package fast.dev.platform.android.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import fast.dev.platform.android.R;
import fast.dev.platform.android.bean.OptionBean;
import fast.dev.platform.android.util.ViewHolder;

@SuppressLint("UseSparseArrays")
public class SingleQuestionOptionListAdapter extends BaseAdapter {

	private List<OptionBean> optionBeans = new ArrayList<OptionBean>();
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private SparseBooleanArray states = new SparseBooleanArray();// 用于记录每道题目中每个问题项是否被选中的状态，用于列表项视图渲染时提取单选钮是否选中状态
	private SparseArray<Integer> selected_states = new SparseArray<Integer>();// 用于记录每道题已选中的答案的index
	private int current_question_index = 0;// 当前问题的index，即记录当前是哪道题

	public SingleQuestionOptionListAdapter(Context context, List<OptionBean> optionBeans) {
		this.mContext = context;
		if (optionBeans != null) {
			this.optionBeans = optionBeans;
		}
		this.mLayoutInflater = LayoutInflater.from(this.mContext);
	}

	/**
	 * 初始化问题答案列表的选中状态集合，将第一项置为选中
	 */
	public void initStates() {
		for (int i = 0; i< optionBeans.size(); i++) {
			if (i == 0) {
				states.put(i, true);
			} else {
				states.put(i, false);
			}
		}
	}
	
	/**
	 * 重置问题答案列表的选中状态集合，如果之前有选择过则将答案置为之前选择的那项，否则执行初始化操作
	 * 
	 * @param to_question_index 要跳转的问题的index
	 */
	public void resetStates(int to_question_index) {
		for (int i = 0; i< optionBeans.size(); i++) {
			if (selected_states.get(to_question_index) == null) {// 如果要跳转的问题还未选择过答案就做初始化操作
				initStates();
			} else {// 如果要跳转的问题已选择过答案，则从历史记录集合中取出之前选中的答案的index
				if (i == selected_states.get(to_question_index)) {
					states.put(i, true);
				} else {
					states.put(i, false);
				}
			}
		}
		this.current_question_index = to_question_index;
	}
	
	@Override
	public int getCount() {
		return optionBeans.size();
	}

	@Override
	public OptionBean getItem(int position) {
		if (optionBeans.isEmpty()) {
			return null;
		}
		return optionBeans.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.single_item, parent, false);
		}
		final RadioButton radio_item = ViewHolder.get(convertView, R.id.radio_item);
		radio_item.setText(optionBeans.get(position).getOPTION_CONTENT());
		radio_item.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				for (int i = 0; i < states.size(); i++) {
					states.put(i, false);
				}
				states.put(position, true);
				SingleQuestionOptionListAdapter.this.notifyDataSetChanged();
				selected_states.put(current_question_index, position);// 将当前问题选中的答案的index保存
			}

		});
		
		radio_item.setChecked(states.get(position));

		return convertView;
	}

	public int getCheckPosition() {
		for (int i = 0; i < optionBeans.size(); i++) {
			if (states.get(i) == true) {
				return i;
			}
		}
		return 0;
	}

}
