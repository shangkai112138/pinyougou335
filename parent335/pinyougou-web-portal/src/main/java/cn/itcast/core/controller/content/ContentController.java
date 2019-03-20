package cn.itcast.core.controller.content;

import cn.itcast.core.pojo.ad.Content;
import cn.itcast.core.pojo.item.ItemCat;
import cn.itcast.core.service.content.ContentCategoryService;
import cn.itcast.core.service.content.ContentService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Reference
    private ContentService contentService;

    @Reference
    private ContentCategoryService contentCategoryService;

    /**
     * 首页大广告的轮播图
     * @param categoryId
     * @return
     */
    @RequestMapping("/findByCategoryId.do")
    public List<Content> findByCategoryId(Long categoryId){
        return contentService.findByCategoryId(categoryId);
    }

    /**
     * 所有广告
     * @return
     */
    @RequestMapping("/findContent.do")
    public Map<Object,Object> findContent(){
        // Map<Object, Object> content = contentService.findContent();
        return contentService.findContent();
    }

    /**
     * 加载商品分类
     * @param parentId
     * @return
     */
    @RequestMapping("/findByParentId.do")
    public List<ItemCat> findByParentId(Long parentId){

        return contentService.findByParentId(parentId);
    }

}
