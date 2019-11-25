package david.halek.theworkoutassistant;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        // Todo: add other fragments
        switch (position) {
            case 3:
                return new FragExerciseList();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
