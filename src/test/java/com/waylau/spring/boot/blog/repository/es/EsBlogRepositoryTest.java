package com.waylau.spring.boot.blog.repository.es;

import com.waylau.spring.boot.blog.domain.es.EsBlog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsBlogRepositoryTest {

    @Autowired
    private EsBlogRepository esBlogRepository;
    @Before
    public void initRepositoryData(){
        //清除所有数据
        esBlogRepository.deleteAll();
        //初始化
        esBlogRepository.save(new EsBlog("登鹤雀楼","王之涣的登鹤雀楼","百日依山尽，黄河入海流，欲穷千里目，更上一层楼"));
        esBlogRepository.save(new EsBlog("相思","王维的相思","红豆生南国，春来发几枝。愿君多采缉，此物最相思"));
        esBlogRepository.save(new EsBlog("静夜思","李白的静夜思","床前明月光，疑似地上霜。举头望明月，低头思故乡"));
    }
    /**
     * 分页查询博客，带去重功能
     * @return
     */
    @Test
    public void  testFindDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(){
        Pageable pageable = new PageRequest(0,20);
        String title="思";
        String summary = "相思";
        String content="楼";
       Page<EsBlog> page =  esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(title,summary,content,pageable);
       //assertThat(page.getTotalElements()).isEqualTo(2);
        System.out.println("-----------------start 1");
       for(EsBlog blog : page.getContent()){
           System.out.println(blog.toString());
       }
        System.out.println("----------------end 1");

       title="相思";
       summary = "相思";
       content="相思";
        page =  esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(title,summary,content,pageable);
        //assertThat(page.getTotalElements()).isEqualTo(1);
        System.out.println("-----------------start 2");
        for(EsBlog blog : page.getContent()){
            System.out.println(blog.toString());
        }
        System.out.println("----------------end 2");
    }
}
