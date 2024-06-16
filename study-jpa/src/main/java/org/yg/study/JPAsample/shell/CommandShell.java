package org.yg.study.JPAsample.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.yg.study.JPAsample.manytomany.service.GearService;

@ShellComponent
@RequiredArgsConstructor
public class CommandShell {
    private final GearService gearService;

    @ShellMethod("run service")
    public String run() {
        // todo reflection 으로 모든 메서드 실행 가능하게 하기
        gearService.createGears(3);
        return "success";
    }

    @ShellMethod("Execute my service")
    public String runMyService() {
        // 여기서 서비스 호출 로직을 작성합니다.
        // Shell 에서 runMyService 명령어를 실행하면 이 메소드가 실행됩니다.
        // Arg 가 있을경우 method 이름 + arg 이름으로 실행해야 합니다.
        return "MyService is executed.";
    }
}
