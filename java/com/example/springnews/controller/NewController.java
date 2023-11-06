package com.example.springnews.controller;

import com.example.springnews.model.News;
import com.example.springnews.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.LocalDateTime.now;

@Controller
public class NewController {
    @Autowired
    NewsRepository repositoryN;
    @GetMapping("/newsmain")
    public ModelAndView list(){
        List<News> list = repositoryN.findAll();
        ModelAndView mav =new ModelAndView();
        if(list.size() !=0){
            mav.addObject("list",list);
        }
        else{
            mav.addObject("msg","결과가 없습니다.");
        }
        mav.setViewName("newsView");
        return mav;
    }
    @GetMapping(value ="/one")
    @Transactional
    public ModelAndView one(int id){
        ModelAndView mav=new ModelAndView();
        News vo = repositoryN.findById(id).get();
        vo.setCnt(vo.getCnt()+1);
        List<News> list = repositoryN.findAll();
        mav.addObject("list",list);
        mav.setViewName("newsView");
        return mav;
    }
    @GetMapping("/delete")
    @Transactional
    public ModelAndView delete(int id){
        ModelAndView mav=new ModelAndView();
        try{
            repositoryN.deleteById(id);
            mav.addObject("list",repositoryN.findAll());
        }catch(Exception e){
            mav.addObject("msg","삭제하는 동안 오류가 발생했습니다.");
        }
        mav.setViewName("newsView");
        return mav;
    }
    @GetMapping("/search")
    public ModelAndView search(String keyword){
        List<News> list = repositoryN.findByContentContains(keyword);
        ModelAndView mav = new ModelAndView();
        if(list.size()!=0){
            mav.addObject("list",list);
        }else{
            mav.addObject("msg","검색한 결과가 없습니다.");
        }
        mav.setViewName("newsView");
        return mav;
    }
    @GetMapping("/writer")
    public ModelAndView searchName(String name){
        List<News> list=repositoryN.findByWriter(name);
        ModelAndView mav = new ModelAndView();
        if(list.size()!=0){
            mav.addObject("list",list);
        }else{
            mav.addObject("msg","작성자의 글을 찾지 못했습니다.");
        }
        mav.setViewName("newsView");
        return mav;
    }
    @PostMapping("/insert")
    @Transactional
    public ModelAndView insert(News vo){
        ModelAndView mav = new ModelAndView();
        try{
            repositoryN.save(vo);
            mav.addObject("list",repositoryN.findAll());
        }catch(Exception e){
            mav.addObject("msg","글 작성 중 오류가 발생했습니다.");
        }
        mav.setViewName("newsView");
        return mav;
    }
    @PostMapping("/update")
    @Transactional
    public ModelAndView update(News vo){
        ModelAndView mav = new ModelAndView();
        try{
            News oldvo=repositoryN.findById(vo.getId()).get();
            oldvo.setWriter(vo.getWriter());
            oldvo.setTitle(vo.getTitle());
            oldvo.setContent(vo.getContent());
            oldvo.setWriteDate(now());
            oldvo.setCnt(vo.getCnt());
            mav.addObject("list",repositoryN.findAll());
        }catch (Exception e){
            mav.addObject("msg","글 수정 중 오류가 발생했습니다.");
        }
        mav.setViewName("newsView");
        return mav;

    }
    @GetMapping("/part")
    public ModelAndView part(int page, int size) {  // page : 0~
        ModelAndView mav = new ModelAndView();
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<News> pageObj = repositoryN.findAll(pageRequest);
        List<News> list = pageObj.toList();
        mav.setViewName("newsView");
        mav.addObject("list", list);
        return mav;
    }

}
