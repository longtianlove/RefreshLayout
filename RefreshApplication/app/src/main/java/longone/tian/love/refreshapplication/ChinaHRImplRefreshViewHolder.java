package longone.tian.love.refreshapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 作者:王彬龙 邮件:bingodongtian@gmail.com
 * 创建时间:16/5/27 22:35
 */
public class ChinaHRImplRefreshViewHolder extends BGARefreshViewHolder {
    private View mHeaderPull;//下拉头（带箭头）
    private ImageView mHeaderArrow;//下拉箭头
    private TextView mHeaderTip;//下拉文本
    private ImageView mHeaderLoading;//正在加载图片
    private View mHeaderComplete;//加载成功

    private View footerLoadingView;//上拉 正在加载
    private View footerFailedView;//上拉 加载失败
    private View footerCompleteView;//上拉 加载完成

    public ChinaHRImplRefreshViewHolder(Context context, boolean isLoadingMoreEnabled) {
        super(context, isLoadingMoreEnabled);
    }

    //获取头部下拉刷新控件
    @Override
    public View getRefreshHeaderView() {
        if (mRefreshHeaderView == null) {
            mRefreshHeaderView=View.inflate(mContext,R.layout.list_header,null);
            mRefreshHeaderView.setBackgroundColor(Color.TRANSPARENT);
            mHeaderPull=mRefreshHeaderView.findViewById(R.id.list_header_pull);
            mHeaderArrow= (ImageView) mRefreshHeaderView.findViewById(R.id.list_header_arrow);
            mHeaderTip= (TextView) mRefreshHeaderView.findViewById(R.id.list_header_tip);
            mHeaderLoading= (ImageView) mRefreshHeaderView.findViewById(R.id.list_header_loading);
            mHeaderComplete=mRefreshHeaderView.findViewById(R.id.list_header_complete);
        }
        return mRefreshHeaderView;
    }

    //自定义上拉刷新风格
    public View getLoadMoreFooterView() {
        if(mLoadMoreFooterView==null){
            mLoadMoreFooterView=View.inflate(mContext, R.layout.listview_footer, null);
            mLoadMoreFooterView.setBackgroundColor(Color.TRANSPARENT);
            footerLoadingView = mLoadMoreFooterView.findViewById(R.id.listview_footer_loading);
            footerFailedView =  mLoadMoreFooterView.findViewById(R.id.listview_footer_failed);
            footerFailedView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"点击加载失败",Toast.LENGTH_LONG).show();
                }
            });
            footerCompleteView =  mLoadMoreFooterView.findViewById(R.id.listview_footer_complete);
        }
        return mLoadMoreFooterView;
    }
    /**
     * 下拉刷新控件可见时，处理上下拉进度
     *
     * @param scale         下拉过程0 到 1，回弹过程1 到 0，没有加上弹簧距离移动时的比例
     * @param moveYDistance 整个下拉刷新控件paddingTop变化的值，如果有弹簧距离，会大于整个下拉刷新控件的高度
     */
    @Override
    public void handleScale(float scale, int moveYDistance) {
        Log.e("longtianlove---", scale + "****scale");

    }

    @Override
    public void changeToIdle() {
        Log.e("longtianlove---", "changeToIdle********");
//        resetHeaderState();
    }
    /**
     * 进入下拉状态
     */
    @Override
    public void changeToPullDown() {
        Log.e("longtianlove---", "changeToPullDown********");
        RotateAnimation animation = new RotateAnimation(180,0, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(300);
        animation.setFillAfter(true);
        mHeaderTip.setText("下拉刷新~");
        mHeaderArrow.startAnimation(animation);
    }
    /**
     * 进入释放刷新状态
     */
    @Override
    public void changeToReleaseRefresh() {
        Log.e("longtianlove---",  "changeToReleaseRefresh********");
        RotateAnimation animation = new RotateAnimation(0,180, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(300);
        animation.setFillAfter(true);
        mHeaderArrow.startAnimation(animation);
        mHeaderTip.setText("松开刷新~");
    }

    /**
     * 进入正在刷新状态
     */
    @Override
    public void changeToRefreshing() {
        Log.e("longtianlove---",  "changeToRefreshing********");
        mHeaderPull.setVisibility(View.INVISIBLE);
        mHeaderLoading.setVisibility(View.VISIBLE);
        mHeaderComplete.setVisibility(View.INVISIBLE);
        //可放入初始化
        AnimationDrawable loadingDrawable = (AnimationDrawable) mHeaderLoading.getDrawable();
        loadingDrawable.start();
    }



    /**
     * 结束上拉加载
     */
    public void endLoadingMore(){
        footerLoadingView.setVisibility(View.GONE);//上拉 正在加载
        footerFailedView.setVisibility(View.VISIBLE);//上拉 加载失败
        footerCompleteView.setVisibility(View.GONE);//上拉 加载完成
    }
    /**
     * 结束下拉刷新
     */
    @Override
    public void onEndRefreshing() {
        Log.e("longtianlove---", "changeToRefreshing********");
        mHeaderPull.setVisibility(View.INVISIBLE);
        mHeaderLoading.setVisibility(View.INVISIBLE);
        mHeaderComplete.setVisibility(View.VISIBLE);
//        resetHeaderState();
    }
    //重新设置头部状态
    public void resetHeaderState(){
        mHeaderArrow.clearAnimation();
        mHeaderTip.setText("下拉刷新~");
        mHeaderPull.setVisibility(View.VISIBLE);
        mHeaderLoading.setVisibility(View.INVISIBLE);
        mHeaderComplete.setVisibility(View.INVISIBLE);
    }
}
