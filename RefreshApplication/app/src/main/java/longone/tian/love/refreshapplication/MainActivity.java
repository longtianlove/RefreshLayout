package longone.tian.love.refreshapplication;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements BGARefreshLayout.BGARefreshLayoutDelegate{
    TextView click_text;
    private BGARefreshLayout mRefreshLayout;
    ChinaHRImplRefreshViewHolder refreshViewHolder;
    private ListView listView;
    MyAdapter adapter;
    /**
     * 数据
     */
    private List<String> mListData = new ArrayList<String>();
    /**
     * 设置一共请求多少次数据
     */
    private int ALLSUM = 0;
    /**
     * 一次加载数据的条数
     */
    private int DATASIZE = 10;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        init();
        initRefreshLayout();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    void init() {
        click_text= (TextView) this.findViewById(R.id.click_text);
        click_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRefreshLayout.endLoadingMore();//加载成功
            }
        });
        listView = (ListView) this.findViewById(R.id.listview);
         adapter=new MyAdapter(this,mListData);
        listView.setAdapter(adapter);
        setData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mRefreshLayout.endRefreshing();//刷新成功
            }
        });
    }

    private void initRefreshLayout() {
        mRefreshLayout = (BGARefreshLayout) findViewById(R.id.rl_chinarhr_refresh);
        // 为BGARefreshLayout设置代理
        mRefreshLayout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
         refreshViewHolder = new ChinaHRImplRefreshViewHolder(this, true);
        // 设置下拉刷新和上拉加载更多的风格
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);


//        // 为了增加下拉刷新头部和加载更多的通用性，提供了以下可选配置选项  -------------START
//        // 设置正在加载更多时不显示加载更多控件
//        // mRefreshLayout.setIsShowLoadingMoreView(false);
//        // 设置正在加载更多时的文本
//        refreshViewHolder.setLoadingMoreText(loadingMoreText);
//        // 设置整个加载更多控件的背景颜色资源id
//        refreshViewHolder.setLoadMoreBackgroundColorRes(loadMoreBackgroundColorRes);
//        // 设置整个加载更多控件的背景drawable资源id
//        refreshViewHolder.setLoadMoreBackgroundDrawableRes(loadMoreBackgroundDrawableRes);
//        // 设置下拉刷新控件的背景颜色资源id
//        refreshViewHolder.setRefreshViewBackgroundColorRes(refreshViewBackgroundColorRes);
//        // 设置下拉刷新控件的背景drawable资源id
//        refreshViewHolder.setRefreshViewBackgroundDrawableRes(refreshViewBackgroundDrawableRes);
//        // 设置自定义头部视图（也可以不用设置）     参数1：自定义头部视图（例如广告位）， 参数2：上拉加载更多是否可用
////        mRefreshLayout.setCustomHeaderView(mBanner, false);
//        // 可选配置  -------------END
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        //加载最新数据
        ALLSUM++;
        setData();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        //加载更多数据
        return true;
    }

//    // 通过代码方式控制进入正在刷新状态。应用场景：某些应用在activity的onStart方法中调用，自动进入正在刷新状态获取最新数据
//    public void beginRefreshing() {
//        mRefreshLayout.beginRefreshing();
//    }
//
//    // 通过代码方式控制进入加载更多状态
//    public void beginLoadingMore() {
//        mRefreshLayout.beginLoadingMore();
//    }


    /**
     * 添加假数据
     */
    private void setData() {
        for (int i = 0; i < DATASIZE; i++) {
            mListData.add("第" + (ALLSUM * 10 + i) + "条数据");
        }
        adapter.notifyDataSetChanged();
    }


}
