package controller.domino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import service.domino.DominoService;

@Controller
public class DominoController {
	@Autowired
	DominoService dominoService;
	
	@RequestMapping("/domino")
	public String domino(Model model) {
		dominoService.execute(model);
		return "domino/dominoMain";
	}
	
	@RequestMapping("/bDomino")
	public String bDomino(@RequestParam(value = "num") Integer a1, Model model) {
		dominoService.execute1(model, a1);
		return "domino/dominoB";
	}
	
	@RequestMapping("/cDomino")
	public String cDomino(@RequestParam(value = "a1") Integer a1, 
						  @RequestParam(value = "num") Integer b1,
						  Model model) {
		dominoService.execute2(model, a1, b1);
		return "domino/dominoC";
	}
}
