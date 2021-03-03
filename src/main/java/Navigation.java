import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.*;

public class Navigation {

    private NavigationItem navigation;

    public Navigation() {
        navigation = new NavigationItem(-1L, "root", 0L, true, "");

    }

    private void readCsv() {
        File navigations = new File(getClass().getResource("Navigation.csv").getPath());
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(navigations));
            bufferedReader.readLine();//first line is skipped because it is header
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] elements = line.split(";");
                Long parentId = elements[2].equals("NULL") ? 0 : Long.parseLong(elements[2]);
                NavigationItem item = new NavigationItem(Long.valueOf(elements[0]),
                    elements[1], parentId, Boolean.parseBoolean(elements[3]), elements[4]);
                navigation.addChildItem(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void groupItems() {
        for (NavigationItem item : navigation.getChildItems()) {
            if (item.getParentId() != 0) {
                NavigationItem parent = search(navigation, item.getParentId());
                if(parent!=null)
                parent.addChildItem(item);
            }
        }
    }

    private NavigationItem search(NavigationItem root, Long id) {
        if (root.getId().equals(id)) {
            return root;
        } else {
            for (NavigationItem child : root.getChildItems()) {
                NavigationItem item = search(child, id);
                if (item != null) {
                    return item;
                }
            }
        }
        return null;
    }

    public List<NavigationItem> buildNavigation() {
        readCsv();
        groupItems();
        return navigation.getChildItems().stream()
            .filter(it -> it.getParentId() == 0)
            .collect(Collectors.toList());
    }
}

