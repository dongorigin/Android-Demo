package cn.dong.demo.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import cn.dong.demo.R;
import cn.dong.demo.ui.common.BaseActivity;
import cn.dong.demo.ui.common.BaseFragment;
import cn.dong.demo.ui.AnimActivity;
import cn.dong.demo.ui.AutoCompleteActivity;
import cn.dong.demo.ui.ContentProviderActivity;
import cn.dong.demo.ui.DialogActivity;
import cn.dong.demo.ui.DrawerActivity;
import cn.dong.demo.ui.FlowLayoutActivity;
import cn.dong.demo.ui.IntentsActivity;
import cn.dong.demo.ui.PackageManagerActivity;
import cn.dong.demo.ui.PopupWindowActivity;
import cn.dong.demo.ui.ShareActivity;
import cn.dong.demo.ui.SwipeRefreshLayoutActivity;
import cn.dong.demo.ui.TextSizeActivity;
import cn.dong.demo.ui.ViewPagerActivity;
import cn.dong.demo.ui.WebViewActivity;
import cn.dong.demo.ui.anim.AnimationActivity;
import cn.dong.demo.ui.calendar.CalendarActivity;
import cn.dong.demo.ui.fragment.FragmentTabHostActivity;
import cn.dong.demo.ui.fragment.FragmentTestActivity;
import cn.dong.demo.ui.image.DrawableStateActivity;
import cn.dong.demo.ui.image.ImageActivity;
import cn.dong.demo.ui.image.ImageLoaderActivity;
import cn.dong.demo.ui.list.ListViewActivity;
import cn.dong.demo.ui.list.RecyclerViewActivity;
import cn.dong.demo.ui.list.XListViewActivity;
import cn.dong.demo.ui.location.GeocoderActivity;
import cn.dong.demo.ui.measure.MeasureActivity;
import cn.dong.demo.ui.touch.TouchEventActivity;

public class MainFragment extends BaseFragment {
    private static final String TAG = "MainFragment";
    private ListView listView;

    private DemoInfo[] demos = {new DemoInfo("Calendar", CalendarActivity.class),
            new DemoInfo("FlowLayout", FlowLayoutActivity.class),
            new DemoInfo("Measure", MeasureActivity.class),
            new DemoInfo("Animation", AnimationActivity.class),
            new DemoInfo("SwipeRefreshLayout", SwipeRefreshLayoutActivity.class),
            new DemoInfo("FragmentTabHost", FragmentTabHostActivity.class),
            new DemoInfo("RecyclerView", RecyclerViewActivity.class),
            new DemoInfo("PackageManager", PackageManagerActivity.class),
            new DemoInfo("Common Intents", IntentsActivity.class),
            new DemoInfo("Location", GeocoderActivity.class),
            new DemoInfo("TouchEvent", TouchEventActivity.class),
            new DemoInfo("PopupWindow", PopupWindowActivity.class),
            new DemoInfo("DrawableState", DrawableStateActivity.class),
            new DemoInfo("XListView", XListViewActivity.class),
            new DemoInfo("ListView", ListViewActivity.class),
            new DemoInfo("Dialog", DialogActivity.class),
            new DemoInfo("AutoComplete", AutoCompleteActivity.class),
            new DemoInfo("FragmentTest", FragmentTestActivity.class),
            new DemoInfo("ViewPager", ViewPagerActivity.class),
            new DemoInfo("ImageLoader", ImageLoaderActivity.class),
            new DemoInfo("TextSize", TextSizeActivity.class),
            new DemoInfo("ContentProvider", ContentProviderActivity.class),
            new DemoInfo("Share", ShareActivity.class),
            new DemoInfo("WebView", WebViewActivity.class),
            new DemoInfo("Anim", AnimActivity.class), new DemoInfo("Image", ImageActivity.class),
            new DemoInfo("Drawer", DrawerActivity.class),
    };

    static class DemoInfo {
        private String title;
        private String desc;
        private Class<? extends Activity> clazz;

        public DemoInfo(String title, Class<? extends Activity> clazz) {
            this(title, "", clazz);
        }

        public DemoInfo(String title, String desc, Class<? extends Activity> clazz) {
            this.title = title;
            this.desc = desc;
            this.clazz = clazz;
        }
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.main_fragment;
    }

    @Override
    protected void initPageView(View rootView) {
        listView = (ListView) rootView.findViewById(android.R.id.list);
    }

    @Override
    protected void initPageViewListener() {
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, demos[position].clazz);
                intent.putExtra(BaseActivity.EXTRA_TITLE, demos[position].title);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void process(Bundle savedInstanceState) {
        listView.setAdapter(new MainListAdapter());

        mContext.enableActionBarAutoHide(listView);
    }

    class MainListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return demos.length;
        }

        @Override
        public Object getItem(int position) {
            return demos[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView =
                        LayoutInflater.from(mContext).inflate(R.layout.main_item, parent, false);
            }
            DemoInfo item = demos[position];
            TextView title = (TextView) convertView.findViewById(R.id.title);
            title.setText(item.title);
            return convertView;
        }

    }

}
