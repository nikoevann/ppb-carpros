package com.e.porsche.ui.constructor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.porsche.managers.CarsUtil
import com.e.porsche.models.ConstructorCars
import com.e.porsche.models.ConstructorColor
import com.e.porsche.ui.constructor.constModels.*

class CarConstViewModel: ViewModel() {

//    val modelItems = MutableLiveData<ArrayList<ModelDetailItem>>()
    val currentCarImages = MutableLiveData<ArrayList<Int>>()
    val constSett = MutableLiveData<ArrayList<ConstCar>>()

    fun loadData() {
        getCarByTypes(CarsUtil.BasicColors.WHITE, CarsUtil.ConstructorColorsType.BASIC)?.let {
            currentCarImages.value = it.images
        }

        val sett: ArrayList<ConstCar> = arrayListOf(

            ConstColors(mainTitle = "Екстер'єр", title = "Колір екстер'єру", colors = arrayListOf(
                SubColors(CarsUtil.ConstructorColorsType.BASIC.value, CarsUtil.constructorColors[0], CarsUtil.constructorColors[1], CarsUtil.constructorColors[2], CarsUtil.constructorColors[3]),
                SubColors(CarsUtil.ConstructorColorsType.METALLIC.value, CarsUtil.constructorColors[4], CarsUtil.constructorColors[5], CarsUtil.constructorColors[6], CarsUtil.constructorColors[7]),
                SubColors(CarsUtil.ConstructorColorsType.SPECIAL.value, CarsUtil.constructorColors[8], CarsUtil.constructorColors[9], CarsUtil.constructorColors[10], CarsUtil.constructorColors[11])
            )),

            ConstSett(title = "Аксесуари для коліс", subsett = arrayListOf(
                ConstSubSett("Колеса пофарбовані в колір кузова", "40 000.00 UAH"),
                ConstSubSett("Колеса пофарбовані в чорний колір", "40 000.00 UAH"),
                ConstSubSett("Колеса пофарбовані в чорний матовий колір", "40 000.00 UAH"),
                ConstSubSett("Колеса пофарбовані в сірий колір", "30 000.00 UAH"),
                ConstSubSett("Центри коліс з кольоровим логотипом", "30 000.00 UAH"),
                ConstSubSett("Колеса пофарбовані в чорний колір", "6 000.00 UAH")
            )),


            ConstSett(mainTitle = "Інтер'єр", title = "Декоративна строчка та центри сидінь", subsett = arrayListOf(
                ConstSubSett("Центральна частина сидінь оздобленна шкірою контрастного кольору", "16 000.00 UAH"),
                ConstSubSett("Пакет оздоблення салону декоративною строчкою контрастного кольору", "68 000.00 UAH"),
                ConstSubSett("Розширений пакет оздоблення салону декоративною строчкою контрастного кольору ", "87 000.00 UAH"),
                ConstSubSett("Оздоблення шкірою рульового колеса з декоративною строчкою контрастного кольору", "15 000.00 UAH"),
                ConstSubSett("Внутрішні дверні пороги з оздобленням шкіри та декоративної строчки контрастного кольору", "20 000.00 UAH"),
                ConstSubSett("Шкіряні спинки спортивних сидіннь Plus, з декоративною строчкою", "49 000.00 UAH")
            )),

            ConstSett(mainTitle = "Опції", title = "Декоративна строчка та центри сидінь", subsett = arrayListOf(
                ConstSubSett("Логотип \"718\"", "0.00 UAH"),
                ConstSubSett("Відсутність напису з назвою моделі, на кузові автомобіля", "0.00 UAH"),
                ConstSubSett("Логотип \"718\" (пофарбований)", "5 000.00 UAH"),
                ConstSubSett("Назва моделі (пофарбована)", "8 000.00 UAH"),
                ConstSubSett("Позначення моделі на дверях чорного кольору", "7 000.00 UAH"),
                ConstSubSett("Позначення моделі на дверях сріблястого кольору", "7 000.00 UAH"),
                ConstSubSett("Бічні віконні планки та трикутні накладки на стекла, чорного кольору ", "16 000.00 UAH"),
                ConstSubSett("Пакет оздоблення екстер'єру SportDesign", "83 000.00 UAH")
            )),


            ConstSett(title = "Трансмісія / Шасі", subsett = arrayListOf(
                ConstSubSett("6-ступенева МКПП", "0.00 UAH"),
                ConstSubSett("Автоматична коробка перемикання передач Porsche (PDK)", "94 000.00 UAH"),
                ConstSubSett("Спортивна підвіска PASM (заниження дорожнього просвіту на 10 мм)", "48 000.00 UAH"),
                ConstSubSett("Система регулювання крутного моменту Porsche (PTV), з механічним блокуванням заднього диференціала", "44 000.00 UAH"),
                ConstSubSett("Пакет Sport Chrono", "60 000.00 UAH"),
                ConstSubSett("Спортивні вихлопні труби в срібному кольорі", "18 000.00 UAH"),
                ConstSubSett("Спортивні вихлопні труби в чорному кольорі ", "18 000.00 UAH"),
                ConstSubSett("Паливний бак 64 л", "4 000.00 UAH"),
                ConstSubSett("Керамічні композитні гальма Porsche (PCCB)", "243 000.00 UAH"),
                ConstSubSett("Підсилювач керма Plus", "9 000.00 UAH")
            )),

            ConstSett(title = "Система освітлення", subsett = arrayListOf(
                ConstSubSett("Бі-ксенонові фари, включаючи систему динамічного освітлення (Porsche Dynamic Light System - PDLS)", "29 000.00 UAH"),
                ConstSubSett("Тоновані задні ліхтарі", "16 000.00 UAH"),
                ConstSubSett("Пакет освітлення Light design", "10 000.00 UAH")
            )),

            ConstSett(mainTitle = "Аксесуари tequipment",title = "Колеса та аксесуари", subsett = arrayListOf(
                ConstSubSett("Декоративні ковпачки на колісні ніпелі (чорного кольору з кольоровим гербом Porsche)", "2 306.00 UAH"),
                ConstSubSett("Декоративні ковпачки на колісні вентилі (сріблястого кольору з кольоровим гербом Porsche)", "2 306.00 UAH"),
                ConstSubSett("Декоративні ковпачки на колісні вентилі (сріблястого кольору з одноколірним гербом Porsche)", "2 306.00 UAH")
            ))
        )

        constSett.value = sett
        
    }
    
    fun setCurrentCarColor(color: ConstructorColor) {
        getCarByTypes(color.colorType, color.type)?.let {
            currentCarImages.value = it.images
        }
    }

    fun getCarByTypes(type: CarsUtil.ColorType, colorType: CarsUtil.ConstructorColorsType) : ConstructorCars? {
        return CarsUtil.constructorCars.firstOrNull { (it.type == type && it.colorType == colorType) }
    }

}
