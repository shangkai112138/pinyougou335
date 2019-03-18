package cn.itcast.core.controller.spec;

import cn.itcast.core.entity.PageResult;
import cn.itcast.core.entity.Result;
import cn.itcast.core.pojo.specification.Specification;
import cn.itcast.core.service.spec.SpecService;
import cn.itcast.core.vo.SpecVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/specification")
public class SpecController {

    @Reference
    private SpecService specService;

    @RequestMapping("/add.do")
    public Result add(@RequestBody SpecVo specVo) {
        try {
            specService.add(specVo);
            return new Result(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败");
        }
    }

    @RequestMapping("/search.do")
    public PageResult search(Integer page, Integer rows, @RequestBody Specification specification){
        return specService.search(page, rows, specification);
    }

    @RequestMapping("/findOne.do")
    public SpecVo findOne(Long id){
        return specService.findOne(id);
    }

    @RequestMapping("/update.do")
    public Result update(@RequestBody SpecVo specVo){
        try {
            specService.update(specVo);
            return new Result(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "更新失败");
        }
    }


}
