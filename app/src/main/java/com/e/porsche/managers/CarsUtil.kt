package com.e.porsche.managers

import com.e.porsche.R
import com.e.porsche.models.*

object CarsUtil {

    private val images = arrayListOf(
        R.drawable.image_1_911_carrera_4s_cabriolet,
        R.drawable.image_2_911_carrera_4s_cabriolet,
        R.drawable.image_3_911_carrera_4s_cabriolet,
        R.drawable.image_4_911_carrera_4s_cabriolet,
        R.drawable.image_5_911_carrera_4s_cabriolet,
        R.drawable.image_6_911_carrera_4s_cabriolet
    )

    fun getModelsCars() : ArrayList<Model> {
        return arrayListOf(
            Model("718", R.drawable.model_718),
            Model("911", R.drawable.model_911),
            Model("Panamera", R.drawable.model_panamera),
            Model("Macan", R.drawable.model_macan),
            Model("Cayenne", R.drawable.model_cayenne))
    }

    fun getCarsByModel(model: Model) : ArrayList<Car> {
        return when(model.name) {
            "718" -> { getFirstModelCars() }
            "911" -> { getSecondModelCars() }
            else -> { ArrayList() }
        }
    }

    private fun getFirstModelCars() : ArrayList<Car> {
        return arrayListOf(
            Car(CarType.CAR_718_CAYMAN, "4.6 c", "RWD","8,3 - 7,3", "184 - 167", "718 Cayman", "1 786 785 M", R.drawable.car_718_cayman, "300 hp", "275 км/h", images),
            Car(CarType.CAR_718_CAYMAN,"4.6 c", "RWD", "8,3 - 7,3", "184 - 167","718 Cayman S", "2 159 850 M", R.drawable.car_718_cayman_s, "350 hp", "285 км/h", images),
            Car(CarType.CAR_718_CAYMAN,"4.6 c", "RWD", "8,3 - 7,3", "184 - 167","718 Cayman S", "2 159 850 M", R.drawable.car_718_cayman_s, "350 hp", "285 км/h", images),
            Car(CarType.CAR_718_CAYMAN,"4.6 c", "RWD", "8,3 - 7,3", "184 - 167","718 Cayman S", "2 159 850 M", R.drawable.car_718_cayman_s, "350 hp", "285 км/h", images),
            Car(CarType.CAR_718_BOXSTER,"4.6 c", "RWD","8,3 - 7,3", "184 - 167", "718 Cayman Boxster", "1 851 300 M", R.drawable.car_718_boxter, "300 hp", "275 км/h", images),
            Car(CarType.CAR_718_BOXSTER,"4.6 c", "RWD","8,3 - 7,3", "184 - 167", "718 Cayman Boxster S", "2 224 365 M", R.drawable.car_718_boxter_s, "350 hp", "285 км/h", images),
            Car(CarType.CAR_718_GTS, "4.6 c", "RWD","8,3 - 7,3", "184 - 167","718 Cayman GTS", "2 474 010 M", R.drawable.car_718_cayman_gts_1, "365 hp", "290 км/h", images),
            Car(CarType.CAR_718_GTS,"4.6 c", "RWD", "8,3 - 7,3", "184 - 167","718 Cayman GTS", "2 535 720 M", R.drawable.car_718_cayman_gts_2, "365 hp", "290 км/h", images)
        )
    }

    private fun getSecondModelCars() : ArrayList<Car> {
        return arrayListOf(
            Car(CarType.CAR_911_CARRERA_S,"4.6 c", "AWD","8,3 - 7,3", "184 - 167", "911 Carrera S", "3 764 310.00 M", R.drawable.car_911_carrera_s, "450 hp", "308 км/h", images),
            Car(CarType.CAR_911_CARRERA_S,"4.6 c", "AWD","8,3 - 7,3", "184 - 167", "911 Carrera 4S", "4 002 735.00  M", R.drawable.car_911_carrera_4s, "450 hp", "306 км/h", images),
            Car(CarType.CAR_911_CARRERA_S,"4.6 c", "AWD","8,3 - 7,3", "184 - 167", "911 Carrera 4S Cabriolet", "4 440 315 M", R.drawable.car_911_carrera_4s_cabriolet, "450 hp", "304 км/h", images),
            Car(CarType.CAR_911_CARRERA_S,"4.6 c", "AWD","8,3 - 7,3", "184 - 167", "911 Carrera 4S Cabriolet", "4 440 315 M", R.drawable.car_911_carrera_4s_cabriolet, "450 hp", "304 км/h", images),
            Car(CarType.CAR_911_GT3,"4.6 c","AWD", "8,3 - 7,3", "184 - 167","911 GT3 RS", "5 952 210 M", R.drawable.car_911_gt3_rs, "520 hp", "312 км/h", images),
            Car(CarType.CAR_911_GT3,"4.6 c", "AWD", "8,3 - 7,3", "184 - 167","911 GT3 RS", "5 952 210 M", R.drawable.car_911_gt3_rs, "520hp", "312 км/h", images)
        )
    }

    fun getAccessories() : ArrayList<Int> {
        return arrayListOf(R.drawable.acc_1, R.drawable.acc_1, R.drawable.acc_1, R.drawable.acc_1)
    }

    val dilers = arrayOf(
        "Central Porsche",
        "Porsche Menteng",
        "Porsche Tembalang",
        "Porsche Alam Sutra",
        "Porsche Gunawarman"
    )


    val constructorCars = arrayListOf(
        ConstructorCars(BasicColors.WHITE, ConstructorColorsType.BASIC, arrayListOf(R.drawable.cayman_white_1, R.drawable.cayman_white_2, R.drawable.cayman_white_3, R.drawable.cayman_white_4)),
        ConstructorCars(BasicColors.BLACK, ConstructorColorsType.BASIC, arrayListOf(R.drawable.cayman_black_1, R.drawable.cayman_black_2, R.drawable.cayman_black_3, R.drawable.cayman_black_4)),
        ConstructorCars(BasicColors.RED, ConstructorColorsType.BASIC, arrayListOf(R.drawable.cayman_red_1, R.drawable.cayman_red_2, R.drawable.cayman_red_3, R.drawable.cayman_red_4)),
        ConstructorCars(BasicColors.YELLOW, ConstructorColorsType.BASIC, arrayListOf(R.drawable.cayman_yellow_1, R.drawable.cayman_yellow_2, R.drawable.cayman_yellow_3, R.drawable.cayman_yellow_4)),

        ConstructorCars(MetallicColors.WHITE, ConstructorColorsType.METALLIC, arrayListOf(R.drawable.cayman_white_metallic_1, R.drawable.cayman_white_metallic_2, R.drawable.cayman_white_metallic_3, R.drawable.cayman_white_metallic_4)),
        ConstructorCars(MetallicColors.BLACK, ConstructorColorsType.METALLIC, arrayListOf(R.drawable.cayman_black_metallic_1, R.drawable.cayman_black_metallic_2, R.drawable.cayman_black_metallic_3, R.drawable.cayman_black_metallic_4)),
        ConstructorCars(MetallicColors.BLUE, ConstructorColorsType.METALLIC, arrayListOf(R.drawable.cayman_blue_metallic_1, R.drawable.cayman_blue_metallic_2, R.drawable.cayman_blue_metallic_3, R.drawable.cayman_blue_metallic_4)),
        ConstructorCars(MetallicColors.GREY, ConstructorColorsType.METALLIC, arrayListOf(R.drawable.cayman_grey_metallic_1, R.drawable.cayman_grey_metallic_2, R.drawable.cayman_grey_metallic_3, R.drawable.cayman_grey_metallic_4)),

        ConstructorCars(SpecialColors.RED, ConstructorColorsType.SPECIAL, arrayListOf(R.drawable.cayman_red_spec_1, R.drawable.cayman_red_spec_2, R.drawable.cayman_red_spec_3, R.drawable.cayman_red_spec_4)),
        ConstructorCars(SpecialColors.GREY, ConstructorColorsType.SPECIAL, arrayListOf(R.drawable.cayman_grey_spec_1, R.drawable.cayman_grey_spec_2, R.drawable.cayman_grey_spec_3, R.drawable.cayman_grey_spec_4)),
        ConstructorCars(SpecialColors.ORANGE, ConstructorColorsType.SPECIAL, arrayListOf(R.drawable.cayman_orange_spec_1, R.drawable.cayman_orange_spec_2, R.drawable.cayman_orange_spec_3, R.drawable.cayman_orange_spec_4)),
        ConstructorCars(SpecialColors.BLUE, ConstructorColorsType.SPECIAL, arrayListOf(R.drawable.cayman_blue_spec_1, R.drawable.cayman_blue_spec_2, R.drawable.cayman_blue_spec_3, R.drawable.cayman_blue_spec_4))

    )

    val constructorColors = arrayListOf(
        ConstructorColor("0.00 UAH", BasicColors.WHITE, ConstructorColorsType.BASIC, R.color.basic_white),
        ConstructorColor("0.00 UAH", BasicColors.BLACK, ConstructorColorsType.BASIC, R.color.basic_black),
        ConstructorColor("0.00 UAH", BasicColors.RED, ConstructorColorsType.BASIC, R.color.basic_red),
        ConstructorColor("0.00 UAH", BasicColors.YELLOW, ConstructorColorsType.BASIC, R.color.basic_yellow),

        ConstructorColor("28 000.00 UAH", MetallicColors.WHITE, ConstructorColorsType.METALLIC, R.color.met_white),
        ConstructorColor("28 000.00 UAH", MetallicColors.BLACK, ConstructorColorsType.METALLIC, R.color.met_black),
        ConstructorColor("28 000.00 UAH", MetallicColors.BLUE, ConstructorColorsType.METALLIC, R.color.met_blue),
        ConstructorColor("28 000.00 UAH", MetallicColors.GREY, ConstructorColorsType.METALLIC, R.color.met_grey),

        ConstructorColor("78 000.00 UAH", SpecialColors.RED, ConstructorColorsType.SPECIAL, R.color.spec_red),
        ConstructorColor("78 000.00 UAH", SpecialColors.GREY, ConstructorColorsType.SPECIAL, R.color.spec_grey),
        ConstructorColor("78 000.00 UAH", SpecialColors.ORANGE, ConstructorColorsType.SPECIAL, R.color.spec_orange),
        ConstructorColor("78 000.00 UAH", SpecialColors.BLUE, ConstructorColorsType.SPECIAL, R.color.spec_blue)
    )

    enum class ConstructorColorsType(val value: String) {
        BASIC("Стандартний колір"),
        METALLIC("Металізована емаль"),
        SPECIAL("Спеціальний колір"),
    }

    interface ColorType {}

    enum class BasicColors(val value: String) : ColorType{
        WHITE("Білий (White)"),
        BLACK("Чорний (Black)"),
        RED("Яскраво-червоний (Guard Red)"),
        YELLOW("Жовтий (Racing Yellow)")
    }

    enum class MetallicColors(val value: String) : ColorType {
        WHITE("Білий (Carrara White Metallic)"),
        BLACK("Чорний (Jet Black Metallic)"),
        BLUE("Темно-синій (Night Blue Metallic)"),
        GREY("Сірий (Agate Grey Metallic)")
    }

    enum class SpecialColors(val value: String) : ColorType {
        RED("Яскраво-червоний (Carmine Red)"),
        GREY("Світло-сірий (Grayon)"),
        ORANGE("Помаранчевий (Lava Orange)"),
        BLUE("Блакитний (Miami Blue)")
    }


//    Спеціальний колір
//    Яскраво-червоний carmine red 78.000 UAH
//    153,0,51
//    Світло-сірий crayon
//    199,199,191
//    Помаранчевий lava orange
//    215,54,28
//    Блакитний miami blue
//    0,120,138
//
//
//    Стандартні кольори
//    Білий ргб 239,239,239
//    Чорний 0,0,0
//    Червоний 204,0,51
//    Жовтий 255,204,0
//    Яскраво червоний guard red
//    Жовтий racing yellow
//
//    Металізована емаль
//    Білий carrara white metallic 28.000 UAH
//    239,245,249
//    Чорний jet black metallic 28.000 UAH
//    0,0,0
//    Темно-синій night blue metallic 28.000 UAH
//    29,39,56
//    Сірий agate grey metallic 28
//    51,51,51

}
