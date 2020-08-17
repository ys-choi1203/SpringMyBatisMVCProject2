package service.member;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import command.MemberCommand;
import controller.MailService;
import controller.SmsSend;
import model.MemberDTO;
import repository.MemberRepository;
@Service
public class MemberJoinService {
	@Autowired
	MailService mailService;
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	@Autowired
	MemberRepository memberRepository;
	public Integer numUpdate(String num,String reciver,String userId ) {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setJoinOk(num);
		memberDTO.setUserEmail(reciver);
		memberDTO.setUserId(userId);
		return memberRepository.joinOkUpdate(memberDTO);
	}
	public Integer execute(MemberCommand memberCommand) {
		Integer result = null;
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUserAddr(memberCommand.getUserAddr());
		Timestamp userBirth = 
				new Timestamp(
						memberCommand.getUserBirth().getTime());
		memberDTO.setUserBirth(userBirth);
		memberDTO.setUserEmail(memberCommand.getUserEmail());
		memberDTO.setUserGender(memberCommand.getUserGender());
		memberDTO.setUserId(memberCommand.getUserId());
		memberDTO.setUserName(memberCommand.getUserName());
		memberDTO.setUserPh1(memberCommand.getUserPh1());
		memberDTO.setUserPh2(memberCommand.getUserPh2());
		String interest = "";
		for(String iter : memberCommand.getInterest()) {
			interest += iter + "`";
		}
		memberDTO.setInterest(interest);
		memberDTO.setUserPw(bcryptPasswordEncoder.encode(
				memberCommand.getUserPw()));
		result = memberRepository.insertMember(memberDTO);
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyyMMddHHmmss");
		String num = dateForm.format(new Date());
		String subject = "가입환영인사";
		String content = "<html><body>"
				 + "안녕하세요 '" + memberDTO.getUserId() 
				 +"'님 가입을 환영합니다.<br />"
				 + "아래 링크를 눌러야 가입이 완료가 됩니다. <br />"
				 + "<a href='http://172.16.3.57:8080/SpringMybatisMVCProject/"
				 + "register/memberMail?num="+num+"&reciver="
				 + memberDTO.getUserEmail() 
				 + "&userId="+ memberDTO.getUserId() +"'>"
				 + "<strong><b>"
				 + "가입을 완료하시려면 여기를 눌러주세요.</a>"
				 + "</b></strong>"
				 + "</body></html>";
		if(result != null) {
			try {
				mailService.sendMail(memberDTO.getUserEmail(), 
					memberDTO.getUserId(), content, subject);
				SmsSend ss = new SmsSend();
				ss.smsSend(memberDTO.getUserPh1(), 
						memberDTO.getUserName()+"님 회원가입을 축하합니다.");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
