import java.util.Comparator;

public class Price_Compare implements Comparator<App>{

	@Override
	public int compare(App a, App b) {
		if (a.price > b.price) return 1;
		if (a.price < b.price) return -1;
		return 0;
	}

}
