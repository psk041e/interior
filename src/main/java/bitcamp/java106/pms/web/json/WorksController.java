package bitcamp.java106.pms.web.json;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.domain.Works;
import bitcamp.java106.pms.service.WorksService;

@RestController
@RequestMapping("/works")
public class WorksController {
    
    WorksService worksService;
    
    @Autowired ServletContext sc;
    
    public WorksController(WorksService worksService) {
        this.worksService = worksService;
    }

    @RequestMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(Works works, MultipartFile[] files) throws Exception {
       
        worksService.add(works);
    }
    
    @RequestMapping("delete")
    //@ResponseStatus(HttpStatus.OK) // 응답 상태 코드 값의 기본은 "200(OK)" 이다.
    public void delete(@RequestParam("no") int no) throws Exception {
        worksService.delete(no);
    }
    
    // 여기는 제품 리스틀 간단히 보여주는걸 의미
    @RequestMapping("list")
    public Object list() {       
        return worksService.list();
    }
    
    @RequestMapping("update")
    @ResponseStatus(HttpStatus.OK) // 기본 값이 OK 이다. 
    public void update(Works works) throws Exception {
        worksService.update(works);
    }
    
    // 여기는 상세 보기용
    @RequestMapping("{worksNumber}")
    public Object view(@PathVariable int worksNumber) throws Exception {
        return worksService.getWorksPhotoOption(worksNumber);
    }
    
    // 장바구니 담기
    @RequestMapping("add/buscket")
    public void addBuscket(@RequestParam("worksNumber") int worksNumber,
            @RequestParam("optionNumber") int optionNumber,
            HttpSession session) throws Exception {
        Member member = (Member)session.getAttribute("loginUser");
        worksService.addBuscket(worksNumber, member.getNo(), optionNumber);
    }
    
    // 장바구니 리스트 출력 1 - 공통 공방명 추출
    @RequestMapping("buscketWorkshop")
    public Object buscketWorkshop(HttpSession session) {
        Member member = (Member)session.getAttribute("loginUser");
        return worksService.viewBuscketWorkshopList(member.getNo());
    }
    
    // 장바구니 리스트 출력 2 - 해당 공방 안의 제품 관련 추출
    @RequestMapping("buscketList")
    public Object buscketList(HttpSession session) throws Exception {
        Member member = (Member) session.getAttribute("loginUser");
        List<Object> buscket = worksService.getBuscketList(member.getNo());

        System.out.println(buscket);
        return buscket;
    }
    
    //관리자 판매작품List 
    @RequestMapping("adminList") 
    public Object adminList(int no) {
        return worksService.adminList(no);
    }
    
    //관리자 판매작품 현황
    @RequestMapping("currentState") 
    public Object getCurrentState(@RequestParam("no") int no) {
        return worksService.getCurrentState(no);
    }
    
    //관리자 작품 등록(제품상세)
    @RequestMapping("addWorksDetail") 
    public Object addWorksDetail(MultipartFile files) {
        
        HashMap<String,Object> jsonData = new HashMap<>();
        
        String filesDir = sc.getRealPath("/files");
        
        String filename = UUID.randomUUID().toString();
        jsonData.put("filename", filename);
        jsonData.put("filesize", files.getSize());
        jsonData.put("originname", files.getOriginalFilename());
        try {
            File path = new File(filesDir + "/" + filename);
            System.out.println(path);
            files.transferTo(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonData;
    
    }
    

}







