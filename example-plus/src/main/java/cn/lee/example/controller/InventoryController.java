package cn.lee.example.controller;

import cn.lee.example.dao.InventoryDao;
import cn.lee.example.entity.Inventory;
import cn.lee.example.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.result.R;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author：lix492 @date: 2021/6/29
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/load")
    public ResponseEntity<?> load(){
        List<Inventory> inventoryList=inventoryService.selectAll();
        log.info("inventoryList.size={}",inventoryList.size());
        return ResponseEntity.ok(inventoryList);
    }

    @GetMapping("/load/{goodId}")
    public ResponseEntity<?> load(@PathVariable Long goodId){
        return ResponseEntity.ok(inventoryService.preLoadInventory(goodId));
    }

    @PostMapping("/pre/reduce/{goodId}")
    public ResponseEntity<?> preReduce(@PathVariable Long goodId){
        Long num=inventoryService.preReduceInventory(goodId,1);
        return ResponseEntity.ok(num);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> find(@PathVariable Long id){
        log.info("path param "+id);
        return ResponseEntity.ok(inventoryService.findById(id));
    }


    @PostMapping("/add")
    public ResponseEntity add(){
        Inventory inventory=new Inventory(11L,8);
        Long id=inventoryService.save(inventory);
        log.info("id_worker=={}",id);
        return ResponseEntity.ok(inventory.getId());
    }

    @DeleteMapping("remove/cache")
    public void removeCache(){
        inventoryService.removeCache();
    }
}
