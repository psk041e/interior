// 업무로직 구현체 - 고객사 마다 다른 구현을 할 수 있다.
package bitcamp.java106.pms.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.MainDao;
import bitcamp.java106.pms.dao.WorkshopDao;
import bitcamp.java106.pms.dao.WsphoDao;
import bitcamp.java106.pms.domain.Works;
import bitcamp.java106.pms.domain.Workshop;
import bitcamp.java106.pms.domain.Wspho;
import bitcamp.java106.pms.service.WorkshopService;

@Service
public class WorkshopServiceImpl implements WorkshopService {
    // 해당 메소드의 대해 알고 싶으면 자세한건 인터페이스 참조
    WorkshopDao workshopDao;
    MainDao mainDao;
    WsphoDao wsphoDao;
    
    public WorkshopServiceImpl(WorkshopDao workshopDao, MainDao mainDao, WsphoDao wsphoDao) {
        this.workshopDao = workshopDao;
        this.mainDao = mainDao;
        this.wsphoDao = wsphoDao;
    }

    // 판매자 추가 관련 메소드
    @Override
    public int add(Workshop workshop) {
        return workshopDao.insert(workshop);
    }
    
    @Override
    public int add(Wspho wspho) {
        return wsphoDao.insert(wspho);
    }
    
    @Override
    public int addIntroduce(Wspho wspho) {
        return wsphoDao.insertIntroduce(wspho);
    }
    
    // 판매자 등록 되어있는지 검사!
    @Override
    public boolean isExist(int no) {
        return workshopDao.selectOneNumber(no) > 0 ? true : false;
    }

    @Override
    public List<Workshop> selectPopularList() {
        return mainDao.selectPopularList();
    }
    
    @Override
    public List<Workshop> list(int no) {
        return workshopDao.selectList(no);
    }
    
    @Override
    public List<Workshop> listtwo(int pageNo, int pageSize) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("startRowNo", pageNo);
        params.put("pageSize", pageSize);
        
        return workshopDao.selectListtwo(params);
    }
    
    @Override
    public List<Workshop> listSellerSite() {
        return workshopDao.selectListSellerSite();
    }
    
    @Override
    public List<Workshop> listIntroduce() {
        return workshopDao.selectListIntroduce();
    }
    
    @Override
    public Workshop get(int no) {
        return workshopDao.selectOne(no);
    }
    
    @Override
    public int update(Workshop workshop) {
        return workshopDao.update(workshop);
    }
    
    @Override
    public int updateIntroduce(Workshop workshop) {
        return workshopDao.updateIntroduce(workshop);
    }
    
    @Override
    public int delete(int no) {
        return workshopDao.delete(no);
    }
}







