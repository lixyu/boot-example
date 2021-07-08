//package cn.lee.example.config;
//
//import cn.amorou.uid.worker.DisposableWorkerIdAssigner;
//import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
//import org.springframework.stereotype.Component;
//
///**
// * @author：lix492 @date: 2021/7/1
// */
//@Component
//public class CustomIdGenerator implements IdentifierGenerator {
//    @Override
//    public String nextUUID(Object entity) {
//        return null;
//    }
//
//    @Override
//    public Number nextId(Object entity) {
//        DisposableWorkerIdAssigner workerIdAssigner=new DisposableWorkerIdAssigner();
//        return workerIdAssigner.assignWorkerId();
//    }
//}
