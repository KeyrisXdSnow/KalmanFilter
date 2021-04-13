package org.example.model;


public class KamalFilter {
    // переменные для калмана
    private  static double varVolt = 3; // среднее отклонение (расчет в программе)
    private  static double varProcess = 1; // скорость реакции на изменение (подбирается вручную)
    private static double P = 1.0;
    private static double Xe = 0.0;

    public static double filter(double val) { //функция фильтрации
        double pc = P + varProcess;
        double g = pc / (pc + varVolt);
        P = (1 - g) * pc;
        double xp = Xe;
        double zp = xp;
        Xe = g * (val - zp) + xp; // "фильтрованное" значение
        return (Xe);

    }

    public static void setVarVolt(double varVolt) {
        KamalFilter.varVolt = varVolt;
    }


    public static void setVarProcess(double varProcess) {
        KamalFilter.varProcess = varProcess;
    }
}
