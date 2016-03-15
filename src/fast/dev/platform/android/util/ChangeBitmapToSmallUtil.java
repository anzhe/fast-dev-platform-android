package fast.dev.platform.android.util;

import java.io.File;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;

public class ChangeBitmapToSmallUtil {

	public static int STANDARD_WIDTH = 60;
	public static int STANDARD_HEIGHT = 60;

	public static void changeImageToSmall(int size, String filePath) {
		Bitmap bitmap = null;
		try {
			// 对图片进行压缩
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			// 获取这个图片的宽和高
			bitmap = BitmapFactory.decodeFile(filePath, options);// 此时返回bm为空
			options.inJustDecodeBounds = false;
			// 计算缩放比
			int ratio = (int) (options.outHeight / (float) size);// 配置图片分辨率
			options.inSampleSize = ratio;
			// 重新读入图片，注意这次要把options.inJustDecodeBounds设为false哦
			bitmap = BitmapFactory.decodeFile(filePath, options);

			// 保存入sdCard
			File file = new File(filePath);
			FileOutputStream out = new FileOutputStream(file);
			if (bitmap != null) {
				if (bitmap.compress(Bitmap.CompressFormat.JPEG, 70, out)) {
					out.flush();
					out.close();
				}
			}
			if (bitmap != null) {
				bitmap.recycle();
				System.gc();
			}
		} catch (OutOfMemoryError e) {
			bitmap.recycle();
			System.gc();
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 自定义文件函数 */
	public static void delFile(String path, String strFileName) {
		try {
			File myFile = new File(path, strFileName);
			if (myFile.exists()) {
				myFile.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BitmapDrawable get43DrawableByPath(String filePath) {
		BitmapDrawable bitMapDrawable = null;
		Bitmap resizedBitmap = null;
		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			// 获取这个图片的宽和高
			Bitmap imageBitMap = BitmapFactory.decodeFile(filePath, options);// 此时返回bm为空
			options.inJustDecodeBounds = false;
			// 计算缩放比
			int be = (int) (options.outHeight / (float) 80);// 配置图片分辨率
			if (be <= 0) {
				be = 1;
			}
			options.inSampleSize = be;
			// 重新读入图片，注意这次要把options.inJustDecodeBounds设为false哦
			imageBitMap = BitmapFactory.decodeFile(filePath, options);
			if (!isFitScale(imageBitMap)) {
				return null;
			}
			int newWidth = STANDARD_WIDTH;
			int newHeight = STANDARD_HEIGHT;
			float scaleWidth = (float) newWidth / imageBitMap.getWidth();
			float scaleHeight = (float) newHeight / imageBitMap.getHeight();
			Matrix m = new Matrix();
			m.postScale(scaleWidth, scaleHeight);
			resizedBitmap = Bitmap.createBitmap(imageBitMap, 0, 0, imageBitMap.getWidth(), imageBitMap.getHeight(), m, true);
			bitMapDrawable = new BitmapDrawable(resizedBitmap);
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			System.gc();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitMapDrawable;
	}

	public static BitmapDrawable get43DrawableByPath2(String filePath) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		// 这个isjustdecodebounds很重要
		opt.inJustDecodeBounds = true;
		Bitmap bm = BitmapFactory.decodeFile(filePath, opt);

		// 获取到这个图片的原始宽度和高度
		int picWidth = opt.outWidth;
		int picHeight = opt.outHeight;

		// 获取屏的宽度和高度
		int screenWidth = 300;
		int screenHeight = 300;

		// isSampleSize是表示对图片的缩放程度，比如值为2图片的宽度和高度都变为以前的1/2
		opt.inSampleSize = 1;
		// 根据屏的大小和图片大小计算出缩放比例
		if (picWidth > picHeight) {
			if (picWidth > screenWidth) {
				opt.inSampleSize = picWidth / screenWidth;
			}
		} else {
			if (picHeight > screenHeight) {
				opt.inSampleSize = picHeight / screenHeight;
			}
		}

		// 这次再真正地生成一个有像素的，经过缩放了的bitmap
		opt.inJustDecodeBounds = false;
		bm = BitmapFactory.decodeFile(filePath, opt);
		BitmapDrawable bitMapDrawable = new BitmapDrawable(bm);
		return bitMapDrawable;
	}

	// public static void bitmapOOM(){
	// BitmapFactory.Options opts = new BitmapFactory.Options();
	// opts.inJustDecodeBounds = true;
	// BitmapFactory.decodeFile(imageFile, opts);
	// opts.inSampleSize = computeSampleSize(opts, -1, 128*128);
	// //这里一定要将其设置回false，因为之前我们将其设置成了true
	// opts.inJustDecodeBounds = false;
	// try {
	// Bitmap bmp = BitmapFactory.decodeFile(imageFile, opts);
	// imageView.setImageBitmap(bmp); }
	// catch (OutOfMemoryError err) {
	//
	// }
	//
	// }

	public static boolean isFitScale(Bitmap imageBitMap) {
		if (imageBitMap == null) {
			return false;
		}
		int maxDimension = Math.max(imageBitMap.getWidth(), imageBitMap.getHeight());
		if (maxDimension < STANDARD_WIDTH) {
			return false;
		}
		return true;
	}

	public static boolean deleteFile(File file) {
		if (file.exists()) { // 判断文件是否存在
			if (file.isDirectory()) { // 否则如果它是一个目录
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					// deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
					files[i].delete();
				}
				return true;
			}
		}
		return true;
	}

}
