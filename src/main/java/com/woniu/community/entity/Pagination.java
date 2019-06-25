package com.woniu.community.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: //TODO $
 * @Author jinxuehai
 * @Date $ $
 */
 @Data
public class Pagination {
    private List<Question> questionLIST;
    private boolean showPrevious; //<
    private boolean showNext; //>
    private boolean showFristPage;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();//
    private Integer totalPage;
    //总记录数,当前页码数,每页显示记录数
    public  void  setPagination(Integer totalCount,Integer page,Integer size){
        //总页数
    //    Integer totalPage;
        if (totalCount % size == 0){
            totalPage = totalCount/size;
        }else {
            //证明多一页
            totalPage = totalCount/size +1;
        }

        //第一页和最后一页的处理
        if (page < 1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }
        //肤质给当前类的page
        this.page = page;

        //判断页码显示
        pages.add(page);
        for (int i=1;i<=3;i++){
            if (page-i>0){
                pages.add(0,page-i);//头部插入
            }
            if (page+i <=  totalPage){
                pages.add(page+i);//尾部插入
            }
        }



        //是否展示上一页
        if (page == 1){
            showPrevious = false;
        }else {
            showPrevious = true;
        }
        //是否展示下一页
        if (page.equals(totalPage)){
            showNext = false;
        }else {
            showNext =true;
        }

        //是否展示第一页
        if (pages.contains(1)){
            showFristPage = false;
        }else {
            showFristPage =true;
        }
        //是否展示第一页
        if (pages.contains(totalPage)){
            showEndPage = false;
        }else {
            showEndPage =true;
        }


    }
}
