package com.udemy.in28minutes.springboot.course.myfirstapp.myfirstwebapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {

    /**
     * The @ResponseBody annotation tells a controller that the object returned is automatically serialized into JSON and passed back into the HttpResponse object.
     *
     * Suppose we have a custom Response object:
     * @return
     */
    @RequestMapping("say-hello")
    @ResponseBody
    public String sayHello(){
        return "Hello ! What are you learning today ?";
    }

    /**
     * tomcat jasper kurulduktan sonra, application.properties'a şu satırları giriyoruz:
     *
     * (Bunu yapmadan önce META-INF/resources/WEB-INF/jsp adlı klasörün, src/main/resources altına açılması lazım
     *
     * spring.mvc.view.prefix=/WEB-INF/jsp/
     * spring.mvc.view.suffix=.jsp
     *
     * Suffix ve prefixler ayarlandıktan sonra, yazılan jsp fonksiyonlarının (requestleri böyle olan)
     * karşılıkları, bizim yazdığımız dosyadaki jsp dosyasına denk gelecektir.
     * jsp dosyasının içini html gibi dolduruyoruz. Bu sayede kod yazmadan html yazar gibi siteyi tasarlamış oluyoruz.
     *
     * Fonksiyonun "sayhello" döndürmesinin bir önemi yok. RequestMappingle hangi dosyayı döndüreceği önemli.
     *
     * @return
     */
    @RequestMapping("say-hello-jsp")
    public String sayHelloJsp(){
        return "sayHello";
    }


}
