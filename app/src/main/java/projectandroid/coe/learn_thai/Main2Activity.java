package projectandroid.coe.learn_thai;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private ViewStub stubGrid;
    private ViewStub stubList;
    private ListView listView;
    private GridView gridView;
    private ListViewAdapter listViewAdapter;
    private GridViewAdapter gridViewAdapter;
    private List<Tab1Learning> tab1LearningList;
    private int currentViewMode = 0;

    static final int View_Mode_ListView = 0;
    static final int View_Mode_GridView = 1;


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        stubList = (ViewStub) findViewById(R.id.stub_list);
        stubGrid = (ViewStub) findViewById(R.id.stub_grid);

        stubList.inflate();
        stubGrid.inflate();

        listView = (ListView) findViewById(R.id.mylistview);
        gridView = (GridView) findViewById(R.id.mygridview);

        getTab1LearningList();

        SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
        currentViewMode = sharedPreferences.getInt("currentViewMode", View_Mode_ListView);

        listView.setOnItemClickListener(onItemClick);
        gridView.setOnItemClickListener(onItemClick);


        switchView();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void switchView() {
        if (View_Mode_ListView == currentViewMode) {
            stubList.setVisibility(View.VISIBLE);
            stubGrid.setVisibility(View.GONE);
        } else {
            stubList.setVisibility(View.GONE);
            stubGrid.setVisibility(View.VISIBLE);
        }
        setAdapters();
    }

    private void setAdapters() {
        if (View_Mode_ListView == currentViewMode) {
            listViewAdapter = new ListViewAdapter(this, R.layout.tab1learning, tab1LearningList);
            listView.setAdapter(listViewAdapter);
        } else {
            gridViewAdapter = new GridViewAdapter(this, R.layout.tab1grid, tab1LearningList);
            gridView.setAdapter(gridViewAdapter);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem (int position) {
            switch (position) {
                case 0:
                    Tab1Learning tab1 = new Tab1Learning(R.drawable.num, "Number");
                    return tab1;
                case 1:
                    Tab2Test tab2 = new Tab2Test();
                    return tab2;
                case 2:
                    Tab3More tab3 = new Tab3More();
                    return tab3;
                default:
                    return null;

            }
        }


        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "LEARNING";
                case 1:
                    return "TEST";
                case 2:
                    return "MORE";
            }
            return null;
        }
    }

    public List<Tab1Learning> getTab1LearningList() {
        tab1LearningList = new ArrayList<>();
        tab1LearningList.add(new Tab1Learning(R.drawable.num, "Number"));
        tab1LearningList.add(new Tab1Learning(R.drawable.time, "Times and Date"));
        tab1LearningList.add(new Tab1Learning(R.drawable.greet, "Greeting"));
        tab1LearningList.add(new Tab1Learning(R.drawable.greet, "General Conversation"));
        tab1LearningList.add(new Tab1Learning(R.drawable.direc, "Direction and Place"));
        tab1LearningList.add(new Tab1Learning(R.drawable.tran, "Transportation"));
        tab1LearningList.add(new Tab1Learning(R.drawable.acc, "Accommodation"));
        tab1LearningList.add(new Tab1Learning(R.drawable.emer, "Emergency"));
        tab1LearningList.add(new Tab1Learning(R.drawable.shop, "Shopping"));
        tab1LearningList.add(new Tab1Learning(R.drawable.occ, "Occasion Phrases"));
        return tab1LearningList;
    }

    AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(), tab1LearningList.get(position).getTitle() + " - " + tab1LearningList.get(position), Toast.LENGTH_SHORT).show();
        }
    };





    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_menu_1:
                if (View_Mode_ListView == currentViewMode){
                    currentViewMode = View_Mode_GridView;
                }
                else{
                    currentViewMode = View_Mode_ListView;
                }
                switchView();
                SharedPreferences sharedPreferences = getSharedPreferences("ViewMode",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putInt("currentViewMode",currentViewMode);
                editor.commit();
                break;
        }
        return true;
    }





    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.tab1learning, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
   /* public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

            @Override
            public Fragment getItem (int position) {
                switch (position) {
                    case 0:
                        Tab1Learning tab1 = new Tab1Learning(R.drawable.num, "Number");
                        return tab1;
                    case 1:
                        Tab2Test tab2 = new Tab2Test();
                        return tab2;
                    case 2:
                        Tab3More tab3 = new Tab3More();
                        return tab3;
                    default:
                        return null;

                }
            }


        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "LEARNING";
                case 1:
                    return "TEST";
                case 2:
                    return "MORE";
            }
            return null;
        }
    }*/


}
