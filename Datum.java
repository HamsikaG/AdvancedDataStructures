
public class Datum {
	long freq;
    int key;
	public Datum right;
	public Datum left;

    public Datum(int key, Long val){
        this.key=key;
        this.freq=val;
    }
    public Datum(Long val){
        this.freq=val;
        this.key=-1;
    }

}
