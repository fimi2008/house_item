package top.lionxxw.house.web.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lionxxw.house.entity.Brand;
import top.lionxxw.house.service.BillService;
import top.lionxxw.house.web.base.ServerResult;

import java.util.List;

/**
 * 品牌管理
 * <p>
 *
 * @author lionxxw
 * created on 2018/9/19 10:38
 */
@Controller
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BillService billService;

    @GetMapping("list")
    public String list(Model model) {
        List<Brand> brands = billService.findBrand();
        model.addAttribute("brands", brands);
        return "brand/list";
    }


    @PostMapping("add")
    @ResponseBody
    public ServerResult add(Brand brand) {
        billService.saveBrand(brand);
        return ServerResult.ok();
    }

    @PostMapping("del")
    @ResponseBody
    public ServerResult del(Long id) {
        billService.delBrand(id);
        return ServerResult.ok();
    }
}
