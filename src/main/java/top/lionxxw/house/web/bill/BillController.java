package top.lionxxw.house.web.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lionxxw.house.entity.BillBrandItem;
import top.lionxxw.house.entity.GoodsBill;
import top.lionxxw.house.entity.ItemType;
import top.lionxxw.house.service.BillService;
import top.lionxxw.house.web.base.ServerResult;

import java.util.List;

/**
 * 采购清单

 * created on 2018/9/16 15:50
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Controller
@RequestMapping("bill")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("add")
    @ResponseBody
    public ServerResult add(GoodsBill bill) {
        billService.saveBill(bill);
        return ServerResult.ok();
    }
}
