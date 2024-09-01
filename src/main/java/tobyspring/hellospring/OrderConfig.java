package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import tobyspring.hellospring.data.JdbcOrderRepository;
import tobyspring.hellospring.data.JpaOrderRepository;
import tobyspring.hellospring.order.OrderRepository;
import tobyspring.hellospring.order.OrderService;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class) // 이 설정으로 OrderConfig 값을 가져올 때 DataConfig 값도 모두 가져온다.
public class OrderConfig {
    @Bean
    public OrderRepository orderRepository(DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }

    @Bean
    public OrderService orderService(PlatformTransactionManager transactionManager,
                                     OrderRepository orderRepository) {
        return new OrderService(orderRepository, transactionManager);
    }
}
