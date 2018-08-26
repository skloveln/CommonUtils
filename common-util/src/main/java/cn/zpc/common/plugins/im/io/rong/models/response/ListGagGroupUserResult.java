package cn.zpc.common.plugins.im.io.rong.models.response;

import cn.zpc.common.plugins.im.io.rong.models.Result;
import cn.zpc.common.plugins.im.io.rong.util.GsonUtil;
import java.util.List;

/**
 *  lisitGagGroupUser 返回结果
 */
public class ListGagGroupUserResult extends Result{
	// 群组被禁言用户列表。
	List<GagGroupUser> members;

	public ListGagGroupUserResult(Integer code, String msg, List<GagGroupUser> members) {
		super(code, msg);
		this.members = members;
	}

	public ListGagGroupUserResult(List<GagGroupUser> members) {
		this.members = members;
	}

	/**
	 * 获取members
	 *
	 * @return List<GagGroupUser>
	 */
	public List<GagGroupUser> getMembers() {
		return this.members;
	}

	/**
	 * 设置members
	 *
	 */
	public void setMembers(List<GagGroupUser> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, ListGagGroupUserResult.class);
	}
}
