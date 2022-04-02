package com.mgupta.oracle.data;

import java.util.ArrayList;
import java.util.Collection;

public class DataProviderImpl implements DataProvider {
    private ArrayList data = new ArrayList();

    public DataProviderImpl() {
        data.add("2343225,2345,us_east,RedTeam,ProjectApple,3445s");
        data.add("1223456,2345,us_west,BlueTeam,ProjectBanana,2211s");
        data.add("3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s");
        data.add("1233456,2345,us_west,BlueTeam,ProjectDate,2221s");
        data.add("3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s");
    }

    @Override
    public Collection<String> get() {
        return data;
    }
}
