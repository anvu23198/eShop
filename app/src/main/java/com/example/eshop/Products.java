package com.example.eshop;

public class Products {
    private static final int[] images = {
            R.drawable.phone,
            R.drawable.laptop,
            R.drawable.mouse,
            R.drawable.keyboard,
            R.drawable.monitor,
            R.drawable.headphones};

    private static final String mProducts[] = {
            "iPhone Xs",
            "Acer Nitro 5",
            "Logitech G502 HERO",
            "Corsair K70",
            "Acer KG241P",
            "HyperX Cloud II"};

    private static final double mPrices[] = {
            999.00,
            719.99,
            79.99,
            129.99,
            169.99,
            99.99};

    private static final String mDescriptions[] = {
            "-5.8-inch Super Retina OLED\n -2,436x1,125 pixels\n -iOS 12\n -Dual 12-megapixel camera, 4K Video capture\n -Apple A12 Bionic CPU\n -64GB ",
            "-Windows 10 operating system\n -15.6\" Full HD display\n -8th Gen Intel Core i5-8300H\n -8GB system memory\n -1TB hard drive\n -NVIDEIA GeForce GTX 1050 graphics",
            "-Customizable RGB lighting\n -Mechanical spring button tensioning system\n -11 programmable buttons\n -Highest-performing 16000 dpi sensor",
            "-Cherry MX Red mechanical switches\n -Customizable backlighting\n -Anti-ghosting with full key rollover\n -Dedicated multimedia controls n -Soft-touch wrist rest",
            "-24\" 1920x1080 Full HD resolution\n -AMD Radeon FreeSync Technology\n -1ms fast response time\n -144Hz refresh rate\n -DVI, HDMI, DisplayPort, Speakers",
            "-Over-the-ear design\n -53mm drivers\n USB and 3.5mm interfaces\n -Condenser microphone\n -USB audio control box"};


    private int mIndex;

    Products(){
        this.mIndex = 0;
    }

    Products(int mIndex) {
        this.mIndex = mIndex;
    }

    public String getProduct() {
        return mProducts[mIndex];
    }

    public String getDescription() {
        return mDescriptions[mIndex];
    }

    public int getImage(){
        return images[mIndex];
    }

    public double getPrice(){
        return mPrices[mIndex];
    }

    public int getIndex(){
        return this.mIndex;
    }
    public void setIndex(int mIndex){
        this.mIndex = mIndex;
    }

}
