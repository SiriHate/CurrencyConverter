package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.net.URL;



public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Обьявление переменных для подсчета значений валют (String)
    String value2_buffer = "";

    //Переменные для спиннера (Для работы  с индексами массивов)
    int spinner1_selected, spinner2_selected;

    //Названия валют
    TextView value1_name, value2_name;

    //Обьявление переменных для подсчета значений валют (Double)
    double value2_float_buffer;

    //Переменные для подсчета 1 валюты
    double first_arg, second_arg, result, result_live;

    //Результат (String)
    String result_str = "", result_live_str = "";

    //Переменные вывода значений валют
    TextView value1_display, value2_display;

    //Переменная текущей валюты
    TextView live_currency;

    //Подсчет переменных
    @SuppressLint("DefaultLocale")
    public void Calculate() {
        if (value2_buffer.length() != 0) {
            value2_float_buffer = Double.parseDouble(value2_buffer); // String -> Double
            first_arg = BaseCurrency[spinner1_selected]; //Первая валюта
            second_arg = BaseCurrency[spinner2_selected]; //Вторая валюта
            result = (second_arg / first_arg) * value2_float_buffer; // Результат
            result_str = String.format("%.3f",result); // Double -> String (+Точность 3 знака)
        }
        else {
            result_str = "";
        }
    }

    //Получение страницы
    private static Document getPage() throws IOException {
        String url="https://www.cbr.ru/scripts/XML_daily.asp";
        return Jsoup.parse(new URL(url), 3000);
    }

    //Парсинг валюты
    public void pars_currency() throws IOException {
        Document page = getPage();

        //RUB
        BaseCurrency[0] = 1.0;

        //USD
        double USD_v = Double.parseDouble(page.select("Valute[id=R01235] Value").text().replace(',', '.'));
        double USD_x = Double.parseDouble(page.select("Valute[id=R01235] Nominal").text().replace(',', '.'));
        BaseCurrency[1] = USD_v/USD_x;

        //EUR
        double EUR_v = Double.parseDouble(page.select("Valute[id=R01239] Value").text().replace(',', '.'));
        double EUR_x = Double.parseDouble(page.select("Valute[id=R01239] Nominal").text().replace(',', '.'));
        BaseCurrency[2] = EUR_v/EUR_x;

        //CHF
        double CHF_v = Double.parseDouble(page.select("Valute[id=R01775] Value").text().replace(',', '.'));
        double CHF_x = Double.parseDouble(page.select("Valute[id=R01775] Nominal").text().replace(',', '.'));
        BaseCurrency[3] = CHF_v/CHF_x;

        //GBP
        double GBP_v = Double.parseDouble(page.select("Valute[id=R01035] Value").text().replace(',', '.'));
        double GBP_x = Double.parseDouble(page.select("Valute[id=R01035] Nominal").text().replace(',', '.'));
        BaseCurrency[4] = GBP_v/GBP_x;

        //JPY
        double JPY_v = Double.parseDouble(page.select("Valute[id=R01820] Value").text().replace(',', '.'));
        double JPY_x = Double.parseDouble(page.select("Valute[id=R01820] Nominal").text().replace(',', '.'));
        BaseCurrency[5] = JPY_v/JPY_x;

        //UAH
        double UAH_v = Double.parseDouble(page.select("Valute[id=R01720] Value").text().replace(',', '.'));
        double UAH_x = Double.parseDouble(page.select("Valute[id=R01720] Nominal").text().replace(',', '.'));
        BaseCurrency[6] = UAH_v/UAH_x;

        //KZT
        double KZT_v = Double.parseDouble(page.select("Valute[id=R01335] Value").text().replace(',', '.'));
        double KZT_x = Double.parseDouble(page.select("Valute[id=R01335] Nominal").text().replace(',', '.'));
        BaseCurrency[7] = KZT_v/KZT_x;

        //BYN
        double BYN_v = Double.parseDouble(page.select("Valute[id=R01090B] Value").text().replace(',', '.'));
        double BYN_x = Double.parseDouble(page.select("Valute[id=R01090B] Nominal").text().replace(',', '.'));
        BaseCurrency[8] = BYN_v/BYN_x;

        //TRY
        double TRY_v = Double.parseDouble(page.select("Valute[id=R01700J] Value").text().replace(',', '.'));
        double TRY_x = Double.parseDouble(page.select("Valute[id=R01700J] Nominal").text().replace(',', '.'));
        BaseCurrency[9] = TRY_v/TRY_x;

        //CNY
        double CNY_v = Double.parseDouble(page.select("Valute[id=R01375] Value").text().replace(',', '.'));
        double CNY_x = Double.parseDouble(page.select("Valute[id=R01375] Nominal").text().replace(',', '.'));
        BaseCurrency[10] = CNY_v/CNY_x;

        //AUD
        double AUD_v = Double.parseDouble(page.select("Valute[id=R01010] Value").text().replace(',', '.'));
        double AUD_x = Double.parseDouble(page.select("Valute[id=R01010] Nominal").text().replace(',', '.'));
        BaseCurrency[11] = AUD_v/AUD_x;

        //CAD
        double CAD_v = Double.parseDouble(page.select("Valute[id=R01350] Value").text().replace(',', '.'));
        double CAD_x = Double.parseDouble(page.select("Valute[id=R01350] Nominal").text().replace(',', '.'));
        BaseCurrency[12] = CAD_v/CAD_x;

        //PLN
        double PLN_v = Double.parseDouble(page.select("Valute[id=R01565] Value").text().replace(',', '.'));
        double PLN_x = Double.parseDouble(page.select("Valute[id=R01565] Nominal").text().replace(',', '.'));
        BaseCurrency[13] = PLN_v/PLN_x;
    }

    //Значения базовой валюты (Рубль)
    // 1-RUB | 2-USD | 3-EUR | 4-CHF | 5-GBP | 6-JPY | 7-UAH | 8-KZT | 9-BYN | 10-TRY | 11-CNY
    // | 12 - AUD | 13-CAD | 14-PLN
    double[] BaseCurrency = new double[14];

    //Переменные картинок флагов
    ImageView value1_flag, value2_flag;

    //Массив картинок флагов
    private final int[] flags_array = {
            R.drawable.russia_flag, R.drawable.usa_flag,  R.drawable.euro_flag,
            R.drawable.switzerland_flag, R.drawable.unitedkingdom_flag, R.drawable.japan_flag,
            R.drawable.ukraine_flag, R.drawable.kazakhstan_flag, R.drawable.belarus_flag,
            R.drawable.turkey_flag, R.drawable.china_flag, R.drawable.australia_flag,
            R.drawable.canada_flag, R.drawable.poland_flag };

    //Массив названий валют
    String[] Currency_names = {"RUB", "USD", "EUR", "CHF", "GBP", "JPY","UAH", "KZT", "BYN", "TRY",
            "CNY", "AUD", "CAD", "PLN"};

    //Отображение текущего курса валют
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    public void Live_Currency() {
        first_arg = BaseCurrency[spinner1_selected]; //Первая валюта
        second_arg = BaseCurrency[spinner2_selected]; //Вторая валюта
        result_live = (first_arg / second_arg); //Результат
        result_live_str = String.format("%.2f",result_live); // Double -> String (+Точность 3 знака)
        live_currency.setText("1"+" "+Currency_names[spinner1_selected]+" "+"="
                +" "+result_live_str+ " "+Currency_names[spinner2_selected]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Вызов функции парсинга страницы
        try {
            pars_currency();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Переменные кнопок
        Button zero, one, two, three, four, five, six, seven, eight, nine, dot, clear;
        ImageButton change_position, info_page;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        //Привязка ImageView по id
        value1_flag = findViewById(R.id.value1_flag);
        value2_flag = findViewById(R.id.value2_flag);

        //Привязка TextView по id
        //Вывод значений валют
        value1_display = findViewById(R.id.value1);
        value2_display = findViewById(R.id.value2);
        //Названия валют
        value1_name = findViewById(R.id.value1_name);
        value2_name = findViewById(R.id.value2_name);
        //Текущий курс валют
        live_currency = findViewById(R.id.live_currency);

        //Привязка Buttons по id
        zero = findViewById(R.id.key_0);
        one = findViewById(R.id.key_1);
        two = findViewById(R.id.key_2);
        three = findViewById(R.id.key_3);
        four = findViewById(R.id.key_4);
        five = findViewById(R.id.key_5);
        six = findViewById(R.id.key_6);
        seven = findViewById(R.id.key_7);
        eight = findViewById(R.id.key_8);
        nine = findViewById(R.id.key_9);
        dot = findViewById(R.id.key_dot);
        clear = findViewById(R.id.key_clear);

        //Привязка ImageButton по id
        change_position = findViewById(R.id.change_position);
        info_page = findViewById(R.id.info_page);

        //Обработка нажатия кнопки info
        info_page.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, InfoActivity.class)));

        //Обработка нажатий кнопок клавиатуры
        zero.setOnClickListener(v -> {
            if (value2_buffer.length() != 0) {
                value2_buffer = value2_buffer + "0";
                value2_display.setText(value2_buffer);
                Calculate();
                value1_display.setText(result_str);
            }
        });

        one.setOnClickListener(v -> {
            value2_buffer = value2_buffer+"1";
            value2_display.setText(value2_buffer);
            Calculate();
            value1_display.setText(result_str);

        });

        two.setOnClickListener(v -> {
            value2_buffer = value2_buffer+"2";
            value2_display.setText(value2_buffer);
            Calculate();
            value1_display.setText(result_str);
        });

        three.setOnClickListener(v -> {
            value2_buffer = value2_buffer+"3";
            value2_display.setText(value2_buffer);
            Calculate();
            value1_display.setText(result_str);
        });

        four.setOnClickListener(v -> {
            value2_buffer = value2_buffer+"4";
            value2_display.setText(value2_buffer);
            Calculate();
            value1_display.setText(result_str);
        });

        five.setOnClickListener(v -> {
            value2_buffer = value2_buffer+"5";
            value2_display.setText(value2_buffer);
            Calculate();
            value1_display.setText(result_str);
        });

        six.setOnClickListener(v -> {
            value2_buffer = value2_buffer+"6";
            value2_display.setText(value2_buffer);
            Calculate();
            value1_display.setText(result_str);
        });

        seven.setOnClickListener(v -> {
            value2_buffer = value2_buffer+"7";
            value2_display.setText(value2_buffer);
            Calculate();
            value1_display.setText(result_str);
        });

        eight.setOnClickListener(v -> {
            value2_buffer = value2_buffer+"8";
            value2_display.setText(value2_buffer);
            Calculate();
            value1_display.setText(result_str);
        });

        nine.setOnClickListener(v -> {
            value2_buffer = value2_buffer+"9";
            value2_display.setText(value2_buffer);
            Calculate();
            value1_display.setText(result_str);
        });

        dot.setOnClickListener(v -> {
            if (value2_buffer.length() > 0) {
                if (!value2_buffer.contains(".")) {
                    value2_buffer = value2_buffer+".";
                    value2_display.setText(value2_buffer);
                    Calculate();
                    value1_display.setText(result_str);
                }
            }
        });

        clear.setOnClickListener(v -> {
            if (value2_buffer.length() > 0) {
                value2_buffer = value2_buffer.substring(0, value2_buffer.length() - 1);
                value2_display.setText(value2_buffer);
                Calculate();
                value1_display.setText(result_str);
            }
        });

        //Spinner1 + Adapter1
        Spinner spinner1 = findViewById(R.id.value1_select);
        ArrayAdapter<?> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.country, R.layout.color_spinner_layout);
        adapter1.setDropDownViewResource(R.layout.color_spinner_dropdown_layout);
        spinner1.setAdapter(adapter1);
        spinner1.setSelection(1);
        spinner1.setOnItemSelectedListener(this);

        //Spinner2 + Adapter2
        Spinner spinner2 = findViewById(R.id.value2_select);
        ArrayAdapter<?> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.country, R.layout.color_spinner_layout);
        adapter2.setDropDownViewResource(R.layout.color_spinner_dropdown_layout);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(0);
        spinner2.setOnItemSelectedListener(this);

        //Обработка нажатий кнопок с картинками
        //Кнопка смены позиций валют
        change_position.setOnClickListener(v -> {
            spinner1.setSelection(spinner2_selected);
            spinner2.setSelection(spinner1_selected);
        });

    }

    //Функционал Spinner-ов
    @SuppressLint("SetTextI18n")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Условие 1 spinner
        if (parent.getId() == R.id.value1_select) {
            //Условия 1 spinner (Выбранный элемент)
            for (int i = 0; i < 14; i++) {
                if (parent.getSelectedItemPosition() == i) {
                    value1_name.setText(Currency_names[i]);
                    value1_flag.setImageResource(flags_array[i]);
                    spinner1_selected = i;
                    Live_Currency();
                    Calculate();
                    value1_display.setText(result_str);
                }
            }
            //Условие 2 spinner
        } else if (parent.getId() == R.id.value2_select) {
            //Условия 2 spinner (Выбранный элемент)
            for (int i = 0; i < 14; i++) {
                if (parent.getSelectedItemPosition() == i) {
                    value2_name.setText(Currency_names[i]);
                    value2_flag.setImageResource(flags_array[i]);
                    spinner2_selected = i;
                    Live_Currency();
                    Calculate();
                    value1_display.setText(result_str);
                }
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


}
