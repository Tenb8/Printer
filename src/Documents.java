public class Documents {
    private String name;
    private String size;
    private final int TIME;
    public Documents(String name,String size,int TIME){
        this.name=name;
        this.size=size;
        this.TIME=TIME;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public int getTIME() {
        return TIME;
    }

    @Override
    public String toString() {
        return "Имя документа: "+name+" Размер: "+size+" Время печати: "+TIME +" секунд(а)";
    }
}
