package com.interswitchng.smartpossdksample.utils

import android.content.Context
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import com.interswitchng.smartpos.shared.models.posconfig.PrintObject
import com.interswitchng.smartpos.shared.models.posconfig.PrintStringConfiguration

object PrinterUtil {

    private var printObjects: MutableList<PrintObject> = mutableListOf()

    fun resetPrintData() = printObjects.clear()

    fun getPrintData() = printObjects.toList()

    fun addLine(text: String, isTitle: Boolean) {
        printObjects.add(PrintObject.Data(text, if (isTitle) titleConfig() else fieldConfig()))
    }

    fun addBitmap(@DrawableRes drawableResId: Int, isBitmapResource: Boolean, context: Context) {
        val imageBitmap = if (isBitmapResource) {
            BitmapFactory.decodeResource(context.resources, drawableResId)
        } else {
            vectorDrawableToBitmap(context, drawableResId)
        }

        imageBitmap?.let { printObjects.add(PrintObject.BitMap(it)) }
    }

    private fun titleConfig(): PrintStringConfiguration {
        return PrintStringConfiguration(
            isTitle = true,
            isBold = false,
            displayCenter = false
        )
    }

    private fun fieldConfig(): PrintStringConfiguration {
        return PrintStringConfiguration(
            isTitle = false,
            isBold = false,
            displayCenter = false
        )
    }
}