package dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("daoApi")
public class DaoApi implements IDao {
    @Override
    public double getValue() {
        return 220.0;
    }
}
