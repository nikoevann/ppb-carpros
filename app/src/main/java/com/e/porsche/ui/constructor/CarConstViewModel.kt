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

            ConstColors(mainTitle = "Bagian luar", title = "Warna eksterior", colors = arrayListOf(
                SubColors(CarsUtil.ConstructorColorsType.BASIC.value, CarsUtil.constructorColors[0], CarsUtil.constructorColors[1], CarsUtil.constructorColors[2], CarsUtil.constructorColors[3]),
                SubColors(CarsUtil.ConstructorColorsType.METALLIC.value, CarsUtil.constructorColors[4], CarsUtil.constructorColors[5], CarsUtil.constructorColors[6], CarsUtil.constructorColors[7]),
                SubColors(CarsUtil.ConstructorColorsType.SPECIAL.value, CarsUtil.constructorColors[8], CarsUtil.constructorColors[9], CarsUtil.constructorColors[10], CarsUtil.constructorColors[11])
            )),

            ConstSett(title = "Aksesoris ban", subsett = arrayListOf(
                ConstSubSett("Roda dicat dengan warna bodi", "40 000.000"),
                ConstSubSett("Roda dicat hitam", "40 000.000"),
                ConstSubSett("Roda dicat hitam matte", "40 000.000"),
                ConstSubSett("Roda dicat abu-abu", "30 000.000"),
                ConstSubSett("Pusat roda dengan logo warna", "30 000.000"),
                ConstSubSett("Roda dicat hitam", "6 000.000")
            )),


            ConstSett(mainTitle = "Interior", title = "Jahitan interior kursi", subsett = arrayListOf(
                ConstSubSett("Bagian tengah jok didekorasi dengan kulit dengan warna kontras", "16 000.000"),
                ConstSubSett("Paket dekorasi interior dengan jahitan dekoratif dengan warna kontras", "68 000.000"),
                ConstSubSett("Paket dekorasi interior yang diperluas dengan jahitan dekoratif dengan warna kontras ", "87 000.000"),
                ConstSubSett("Trim kulit pada setir dengan jahitan dekoratif dengan warna kontras", "15 000.000"),
                ConstSubSett("Kusen pintu interior dengan trim kulit dan jahitan dekoratif dengan warna kontras", "20 000.000"),
                ConstSubSett("Punggung kulit jok sport Plus, dengan jahitan dekoratif", "49 000.000")
            )),

            ConstSett(mainTitle = "Pilihan", title = "Jahitan interior kursi", subsett = arrayListOf(
                ConstSubSett("Logo \"718\"", "0.00"),
                ConstSubSett("Tidak adanya tulisan dengan nama model di badan mobil", "0.00 UAH"),
                ConstSubSett("Logo \"718\" (berwarna)", "5 000.000"),
                ConstSubSett("Nama model (dilukis)", "8 000.000"),
                ConstSubSett("Penunjukan model pada pintu berwarna hitam", "7 000.000"),
                ConstSubSett("Penunjukan model pada pintu berwarna perak", "7 000.000"),
                ConstSubSett("Bilah jendela samping dan overlay segitiga pada kaca, hitam ", "16 000.000"),
                ConstSubSett("Paket dekorasi eksterior SportDesign", "83 000.000")
            )),


            ConstSett(title = "Transmisi / Sasis", subsett = arrayListOf(
                ConstSubSett("Transmisi manual 6 percepatan", "0.00"),
                ConstSubSett("Gearbox otomatis Porsche (PDK)", "94 000.000"),
                ConstSubSett("Suspensi sport PASM (menurunkan ground clearance hingga 10 mm)", "48 000.000"),
                ConstSubSett("Sistem kontrol torsi Porsche (PTV), dengan penguncian mekanis diferensial belakang", "44 000.000"),
                ConstSubSett("Paket Olahraga Chrono", "60 000.000"),
                ConstSubSett("Pipa knalpot sport dalam warna perak", "18 000.000"),
                ConstSubSett("Pipa knalpot sport berwarna hitam ", "18 000.000"),
                ConstSubSett("Tangki bahan bakar 64 l", "4 000.000"),
                ConstSubSett("Rem Komposit Keramik Porsche (PCCB)", "243 000.000"),
                ConstSubSett("Power steering Plus", "9 000.000")
            )),

            ConstSett(title = "Sistem pencahayaan", subsett = arrayListOf(
                ConstSubSett("Lampu depan bi-xenon, termasuk sistem pencahayaan dinamis (Porsche Dynamic Light System - PDLS)", "29 000.000"),
                ConstSubSett("Lampu belakang berwarna", "16 000.000"),
                ConstSubSett("Paket pencahayaan desain ringan", "10 000.000")
            )),

            ConstSett(mainTitle = "Aksesori peralatan",title = "Roda dan aksesori", subsett = arrayListOf(
                ConstSubSett("Tutup dekoratif untuk pentil roda (hitam dengan lambang Porsche berwarna)", "2 306.000"),
                ConstSubSett("Tutup katup roda dekoratif (warna perak dengan lambang Porsche berwarna)", "2 306.000"),
                ConstSubSett("Tutup katup roda dekoratif (warna perak dengan lambang Porsche satu warna)", "2 306.000")
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
