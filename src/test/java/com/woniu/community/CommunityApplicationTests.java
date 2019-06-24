package com.woniu.community;

import com.woniu.community.util.HttpUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunityApplicationTests {
    @Test
    public void contextLoads() {
        HttpUtils.httpGet("http://localhost:9997/userService/user");
    }

    @Test
    public void loadXml(){
            // 解析books.xml文件
            // 创建SAXReader的对象reader
            SAXReader reader = new SAXReader();
            try {
                // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
                Document document = reader.read(new URL("http://localhost:9997/userService/user"));
                // 通过document对象获取根节点Users
                Element users = document.getRootElement();
                // 通过element对象的elementIterator方法获取迭代器
                Iterator it = users.elementIterator();
                // 遍历迭代器，获取根节点中的信息（书籍）
                while (it.hasNext()) {
                    System.out.println("=====开始遍历某一用户====");
                    Element book = (Element) it.next();
                    // 获取book的属性名以及 属性值
                    List<Attribute> bookAttrs = book.attributes();
                    for (Attribute attr : bookAttrs) {
                        System.out.println("属性名：" + attr.getName() + "--属性值："
                                + attr.getValue());
                    }
                    Iterator itt = book.elementIterator();
                    while (itt.hasNext()) {
                        Element bookChild = (Element) itt.next();
                        System.out.println("节点名：" + bookChild.getName() + "--节点值：" + bookChild.getStringValue());
                    }
                    System.out.println("=====结束遍历某一用户=====");
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
    }
}
