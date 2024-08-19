package com.pluralsight.calcengine;

public class ForLoop {

    public static void main(String[] args) {
        for (var i = 0; i < 10; i++ ) {
            System.out.println(i);
        }

        Machine machine = new Machine(0);
        machine.beanCounter(50);
        System.out.println("COUNT=" + machine.getCount());
        System.out.println("TOTAL=" + Machine.getTotal());

        machine.beanCounter(200);
        System.out.println("COUNT=" + machine.getCount());
        System.out.println("TOTAL=" + Machine.getTotal());

    }
}

class Machine {
    private static Integer total;
    private Integer count;

    static {
        total= 0;
    }

    Machine(Integer startCount) {
        count = startCount;
    }

    void beanCounter(Integer numberOfBeans) {
        count += numberOfBeans;
        total += count;
    }

    public static Integer getTotal() {
        return total;
    }

    public static void setTotal(Integer total) {
        Machine.total = total;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
