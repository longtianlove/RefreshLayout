package longone.tian.love.refreshapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by 58 on 2016/5/27.
 */
public class MyAdapter extends BaseAdapter {
    private List<String> mListData = new ArrayList<String>();
    private Context mcontext;

    public MyAdapter(Context context, List<String> listdatas) {
        this.mListData = listdatas;
        mcontext = context;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.item, null);
            holder = new MyViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        holder.textView.setText(mListData.get(position));
        return convertView;
    }


    public static final class MyViewHolder {
        TextView textView;
    }
}