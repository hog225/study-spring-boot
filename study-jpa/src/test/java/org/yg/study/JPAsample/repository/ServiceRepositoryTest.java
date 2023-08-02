package org.yg.study.JPAsample.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.yg.study.JPAsample.entity.ServiceEntity;
import org.yg.study.JPAsample.entity.ServiceKey;
import org.yg.study.JPAsample.service.ServiceService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("sql")
class ServiceRepositoryTest {
    @Autowired ServiceRepository serviceRepository;

    @Autowired
    ServiceService serviceService;


    @Test
    @Transactional
    @Rollback(value = false)
    void test1() {
        List<ServiceEntity> serviceEntities = new ArrayList<>();
        for(int i=0; i<10; i++) {
            ServiceEntity serviceEntity = ServiceEntity.builder()
                    .key(ServiceKey.builder()
                            .serviceId("serviceId" + i)
                            .serviceType("serviceType" + i)
                            .build())
                    .name("name" + i)
                    .build();
            serviceEntities.add(serviceEntity);
        }
        serviceRepository.saveAll(serviceEntities);
    }

    @Test
    @Rollback(value = false)
    @DisplayName("멀티스레드에서 key 를 읽으면 두개 쓰레드에서 키가 없는 것으로 조회되기 때문에 두 쓰레드 모두 Insert 를 하려해서 에러가 발생한다.")
    void test2() throws InterruptedException {

        //test1 에서 저장하지 않은키
        var serviceKey = new ServiceKey("serviceId0", "serviceType1");
        int numberOfThreads = 2;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for(int i = 0; i < numberOfThreads; i++) {
            int finalI = i;
            service.execute(() -> {
                try {
                    serviceService.modifyOrCreateService(serviceKey, "name" + finalI);
                    System.out.println("Tid : " + finalI);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }

        latch.await();

        ServiceEntity entity = serviceRepository.findById(serviceKey).orElse(null);
        System.out.println(entity);

    }

    @Test
    @Rollback(value = false)
    @DisplayName("낙관적 락 업데이트가 정상적으로 일어난다. ")
    void optimisticLock() throws InterruptedException {

        //test1 에서 저장하지 않은키
        var serviceKey = new ServiceKey("serviceId0", "serviceType0");
        int numberOfThreads = 2;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for(int i = 0; i < numberOfThreads; i++) {
            int finalI = i;
            service.execute(() -> {
                try {
                    serviceService.modifyOrCreateService(serviceKey, "name");
                    System.out.println("Tid : " + finalI);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }

        latch.await();

        ServiceEntity entity = serviceRepository.findById(serviceKey).orElse(null);
        System.out.println(entity);

    }

    @Test
    @DisplayName("비관적 락 - 공유락 테스트 Update 시 공유락으로 자원이 조회 되기 때문에 update 가 실패하고 Deadlock found when trying to get lock 에러가 발생한다. ")
    void test3() throws InterruptedException {

        //test1 에서 저장하지 않은키
        var serviceId = "serviceId2";
        var serviceKey = "99";
        int numberOfThreads = 2;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for(int i = 0; i < numberOfThreads; i++) {
            int finalI = i;
            service.execute(() -> {
                try {
                    serviceService.modifyOrCreateServiceShareLock(serviceId, serviceKey, "name" + finalI + " share lock");
                    System.out.println("Tid : " + finalI);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }

        latch.await();

        ServiceEntity entity = serviceRepository.findById(new ServiceKey(serviceId, serviceKey)).orElse(null);
        System.out.println(entity);

    }



}