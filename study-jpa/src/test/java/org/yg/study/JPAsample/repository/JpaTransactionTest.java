package org.yg.study.JPAsample.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.yg.study.JPAsample.entity.PrimeEntity;
import org.yg.study.JPAsample.entity.ServiceEntity;
import org.yg.study.JPAsample.entity.ServiceKey;
import org.yg.study.JPAsample.service.PrimeService;
import org.yg.study.JPAsample.service.ServiceService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@ActiveProfiles("sql")
class JpaTransactionTest {
    @Autowired ServiceRepository serviceRepository;

    @Autowired
    ServiceService serviceService;

    @Autowired
    PrimeService primeService;

    @Autowired
    PrimeRepository primeRepository;


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
    void testSave() {
        for(int i=0; i<10; i++) {
            ServiceEntity serviceEntity = ServiceEntity.builder()
                    .key(ServiceKey.builder()
                            .serviceId("serviceId" + i)
                            .serviceType("serviceType" + i)
                            .build())
                    .name("name" + i)
                    .build();
            serviceRepository.save(serviceEntity);
        }
    }

    @Test
    @Transactional
    void testSaveWithTransactional() {
        for(int i=0; i<10; i++) {
            ServiceEntity serviceEntity = ServiceEntity.builder()
                    .key(ServiceKey.builder()
                            .serviceId("serviceId" + i)
                            .serviceType("serviceType" + i)
                            .build())
                    .name("name" + i)
                    .build();
            serviceRepository.save(serviceEntity);
        }
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testTransaction() {
        List<ServiceEntity> serviceEntities = new ArrayList<>();
        for(int i=0; i<2; i++) {
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
        serviceRepository.findAll();
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
    @DisplayName("Rad UnCommited transaction - duplicate key 에러 발생 ")
    void transactionRedUncommited() throws InterruptedException {

        //test1 에서 저장하지 않은키
        var serviceKey = new ServiceKey("serviceId0", "serviceType0");
        int numberOfThreads = 2;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for(int i = 0; i < numberOfThreads; i++) {
            int finalI = i;
            service.execute(() -> {
                try {
                    serviceService.modifyOrCreateServiceReadUncommitted(serviceKey, "name");
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
    @DisplayName("Rad UnCommited transaction - duplicate key 에러 발생 ")
    void transactionSerializable() throws InterruptedException {

        //test1 에서 저장하지 않은키
        var serviceKey = new ServiceKey("serviceId0", "serviceType0");
        int numberOfThreads = 2;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for(int i = 0; i < numberOfThreads; i++) {
            int finalI = i;
            service.execute(() -> {
                try {
                    serviceService.modifyOrCreateServiceeSerializable(serviceKey, "name");
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
    @DisplayName("Rad UnCommited transaction/ propagation new - duplicate key 에러 발생 ")
    void transactionRedUncommitedPropagationNew() throws InterruptedException {

        //test1 에서 저장하지 않은키
        var serviceKey = new ServiceKey("serviceId0", "serviceType0");
        int numberOfThreads = 2;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for(int i = 0; i < numberOfThreads; i++) {
            int finalI = i;
            service.execute(() -> {
                try {
                    serviceService.modifyOrCreateServiceReadUncommittedPropagationNew(serviceKey, "name");
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
        assert entity != null;
        serviceRepository.delete(entity);

    }

    @Test
    @Rollback(value = false)
    @DisplayName("Optimistic lock ")
    void transactionOptimisticLock() throws InterruptedException {

        int numberOfThreads = 2;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for(int i = 0; i < numberOfThreads; i++) {
            int finalI = i;
            service.execute(() -> {
                try {
                    primeService.modifyOrCreateService("serviceId0", "name" + finalI);
                    System.out.println("Tid : " + finalI);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }

        latch.await();

        var entity = primeRepository.findById("serviceId0").orElse(null);
        System.out.println(entity);
        assert entity != null;
        primeRepository.delete(entity);

    }

    @Test
    @Rollback(value = false)
    @DisplayName("Rad UnCommited transaction - duplicate key 에러 발생 ")
    void transactionRedUncommitedLock() throws InterruptedException {

        //test1 에서 저장하지 않은키
        var serviceKey = new ServiceKey("serviceId0", "serviceType0");
        int numberOfThreads = 2;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for(int i = 0; i < numberOfThreads; i++) {
            int finalI = i;
            service.execute(() -> {
                try {
                    serviceService.modifyOrCreateServiceReadUncommitted(serviceKey, "name");
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

    /**
     * 1. session 1은 row에 exclusive lock을 획득한다.
     * 2. session 2, 3은 duplicate-key error가 발생하고 이에 따라서 row에 shared lock을 각각 요청한다.
     * 3. 이때 session 1이 rollback을 하게 되면 exclusive lock이 해제되고 이에 따라 session 2, 3의 shared lock이 요청 대기에서 승인 상태로 변경된다.
     * 4. Deadlock: 이 상황에서 session 2, 3은 서로 shared lock을 획득한 상황이기 때문에 exclusive lock을 획득할 수 없게 된다.
     * @throws InterruptedException
     */
    @Test
    @Rollback(value = false)
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
        assert entity != null;
        serviceRepository.delete(entity);


    }



}