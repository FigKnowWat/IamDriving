package com.heroku.fig_know_wat.iamdriving.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.heroku.fig_know_wat.iamdriving.R;
import com.heroku.fig_know_wat.iamdriving.db.DatabaseHelper;
import com.heroku.fig_know_wat.iamdriving.db.JournalORMLite;
import com.j256.ormlite.android.AndroidDatabaseResults;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import org.androidannotations.annotations.EFragment;
import org.w3c.dom.Text;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 13/12/15.
 */
@EFragment
public class JournalFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_journal, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // build your query
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        try {
            Dao<JournalORMLite, Integer> dao = databaseHelper.getDao();
            List<JournalORMLite> list = dao.queryForAll();
            setListAdapter(new JournalListAdapter(getActivity(), R.layout.list_item_journal, list));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static class JournalListAdapter extends ArrayAdapter<JournalORMLite> {
        private LayoutInflater inflater;
        private Context context;
        private List<JournalORMLite> list;

        public JournalListAdapter(Context context, int resource) {
            super(context, resource);
            this.context = context;
        }

        public JournalListAdapter(Context context, int resource, List<JournalORMLite> objects) {
            super(context, resource, objects);
            this.context = context;
            this.list = objects;
        }

        private static class ViewHolder {
            TextView name;
            TextView phoneNumber;
            TextView date;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final View itemView;
            final ViewHolder holder;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                itemView = inflater.inflate(R.layout.list_item_journal, parent, false);
                holder = new ViewHolder();
                holder.name = (TextView) itemView.findViewById(R.id.text_call_name);
                holder.phoneNumber = (TextView) itemView.findViewById(R.id.text_phone_number);
                holder.date = (TextView) itemView.findViewById(R.id.text_date);
                itemView.setTag(holder);
            } else {
                itemView = convertView;
                holder = (ViewHolder) itemView.getTag();
            }

            holder.phoneNumber.setText(list.get(position).getPhoneNumber());
            holder.date.setText(list.get(position).getDate());
            return itemView;
        }
    }
}
