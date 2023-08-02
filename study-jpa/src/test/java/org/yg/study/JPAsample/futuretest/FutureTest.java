package org.yg.study.JPAsample.futuretest;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

public class FutureTest {


    @Test
    public void test() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            // 비동기로 실행할 작업
            System.out.println("비동기 작업 실행");
        }).thenRunAsync(() -> {
            // 이전 작업 실행 후 대기 시간 주기
            try {
                Thread.sleep(1000); // 1초 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("대기 후 작업 실행");
        });

        future.join(); // 작업 완료까지 대기

    }
}
