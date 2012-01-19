package cn.mibcxb.utility.life;

public class VerifyID {

	// wi =2(n-1)(mod 11);加权因子
	private static final int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
			5, 8, 4, 2, 1 };
	// 校验码
	private static final int[] vi = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };
	private static int[] ai = new int[18];

	// 校验身份证的校验码
	public static boolean verifyID(String id) {
		if (id.length() == 15) {
			id = uptoEighteen(id);
		}
		if (id.length() != 18) {
			return false;
		}
		String verify = id.substring(17, 18);
		if (verify.equals(getVerify(id))) {
			return true;
		}
		return false;
	}

	// 15位转18位
	private static String uptoEighteen(String fifteen) {
		StringBuffer eighteen = new StringBuffer(fifteen);
		eighteen = eighteen.insert(6, "19");
		return eighteen.toString();
	}

	// 计算最后一位校验值
	private static String getVerify(String eighteen) {
		int remain = 0;
		if (eighteen.length() == 18) {
			eighteen = eighteen.substring(0, 17);
		}
		if (eighteen.length() == 17) {
			int sum = 0;
			for (int i = 0; i < 17; i++) {
				String k = eighteen.substring(i, i + 1);
				ai[i] = Integer.valueOf(k).intValue();
			}
			for (int i = 0; i < 17; i++) {
				sum += wi[i] * ai[i];
			}
			remain = sum % 11;
		}
		return remain == 2 ? "X" : String.valueOf(vi[remain]);

	}

}
