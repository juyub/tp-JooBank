package news;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
public class NewsController {
	
	@Autowired
	private NewsDAO boardDAO;
	
	@Autowired
	private NewsVO boardVO;
	
	@RequestMapping("/index.action")
	public String index(Model model) {
	    List<NewsVO> boardList = boardDAO.getBoardList();
	    model.addAttribute("boardList", boardList);

	    return "/index.jsp";            
	}

	@RequestMapping("/getNewsList.action")
	public String getBoardList(Model model) {
	    List<NewsVO> boardList = boardDAO.getBoardList();
	    model.addAttribute("boardList", boardList);

	    return "/news/getNewsList.jsp";            
	}
	
	@RequestMapping("/getNews.action")
	public String getBoard(String seq, Model model) {
	    NewsVO board = boardDAO.getBorad(seq);
	    model.addAttribute("board", board);
		
	    return "/news/getNews.jsp";
	}
	
	@RequestMapping("/addNews.action")
	public String addBoard(NewsVO vo) {
		boardDAO.addBoard(vo);
		
		 return "redirect:/getNewsList.action";
	}
	
	@RequestMapping("/updateNews.action")
	public String updateBoard(NewsVO vo) {
		boardDAO.updateBoard(vo);
		
		return "redirect:getNews.action?seq=" + vo.getSeq();
	}
	
	@RequestMapping("/deleteNews.action")
	public String deleteBoard(String seq) {
		boardDAO.deleteBoard(seq);
		
		return "redirect:/getNewsList.action";
	}
    
}
