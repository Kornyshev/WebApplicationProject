package me.kornyshev.WebCalculator.unit.tests;

import lombok.extern.slf4j.Slf4j;
import me.kornyshev.WebCalculator.unit.extensions.annotations.TestCaseId;
import me.kornyshev.WebCalculator.unit.extensions.annotations.UserParameterResolver;
import me.kornyshev.WebCalculator.unit.extensions.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith({UserParameterResolver.class})
public class ParameterResolverTests {

    @TestCaseId("60490")
    @Inject
    @Test
    void doSomethingToTestParameterResolver(User user) {
        assertThat(user.getLogin()).isEqualTo("login");
        assertThat(user.getPassword()).isEqualTo("password");
        assertThat(user.getEmail()).isEqualTo("something@email.com");
        log.info("Injected User: '{}'", user);
    }

}
