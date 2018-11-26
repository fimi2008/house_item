package top.lionxxw.house.web.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lionxxw.house.entity.BillBrandItem;
import top.lionxxw.house.entity.ItemType;
import top.lionxxw.house.service.BillService;
import top.lionxxw.house.web.base.ServerResult;
import top.lionxxw.house.web.type.dto.RelateBrandDTO;
import top.lionxxw.house.web.type.dto.UpdatePriceDTO;
import top.lionxxw.house.web.type.vo.TypeDetailVO;

import java.util.List;

/**
 * type

 * created on 2018/9/16 15:50
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Controller
@RequestMapping("type")
public class TypeController {

    @Autowired
    private BillService billService;
    private final static String DATA = "data";

    @GetMapping("list")
    public String typeList(Model model) {
        List<ItemType> types = billService.findTypes();
        model.addAttribute("types", types);
        return "type/list";
    }

    @PostMapping("add")
    @ResponseBody
    public ServerResult addType(String name) {
        ItemType itemType = new ItemType();
        itemType.setType(name);
        billService.saveType(itemType);
        return ServerResult.ok();
    }

    @PostMapping("del")
    @ResponseBody
    public ServerResult del(Long id) {
        billService.delType(id);
        return ServerResult.ok();
    }

    @GetMapping("detail")
    public String detail(Long id, Model model) {
        TypeDetailVO detail = billService.typeDetail(id);
        model.addAttribute(DATA,detail);
        return "type/detail";
    }

    @PostMapping("relateBrand")
    @ResponseBody
    public ServerResult relateBrand(RelateBrandDTO dto){
        billService.relateBrand(dto);
        return ServerResult.ok();
    }

    @PostMapping("updatePrice")
    @ResponseBody
    public ServerResult updatePrice(UpdatePriceDTO dto){
        billService.updatePrice(dto);
        return ServerResult.ok();
    }

    @PostMapping("delImg")
    @ResponseBody
    public ServerResult delImg(UpdatePriceDTO dto){
        billService.delImg(dto);
        return ServerResult.ok();
    }
}
