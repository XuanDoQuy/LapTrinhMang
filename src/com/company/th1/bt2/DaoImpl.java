package com.company.th1.bt2;

import static com.company.th1.bt2.Server.listData;

public class DaoImpl implements Registry{

    @Override
    public void add(Person p) {
        p.setId(Server.count++);
        System.out.println(p);
        listData.add(p);
    }

    @Override
    public String getPhone(String name) {
        String result = "not found";
        for (Person p : listData){
            if (p.getName().equals(name)){
                result = p.getPhoneNumber();
                break;
            }
        }
        return result;
    }

    @Override
    public Iterable<Person> getALl() {
        return listData;
    }

    @Override
    public Person search(String name) {
        Person result = null;
        for (Person p : listData){
            if (p.getName().equals(name)){
                result = p;
                break;
            }
        }
        return result;
    }
}

interface Registry{
    void add(Person p);
    String getPhone(String name);
    Iterable<Person> getALl();
    Person search(String name);
}
