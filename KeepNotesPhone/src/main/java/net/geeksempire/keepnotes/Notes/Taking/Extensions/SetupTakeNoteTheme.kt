package net.geeksempire.keepnotes.Notes.Taking.Extensions

import android.content.res.ColorStateList
import net.geeksempire.keepnotes.Notes.Taking.TakeNote
import net.geeksempire.keepnotes.R

fun TakeNote.setupTakeNoteTheme() {

    takeNoteLayoutBinding.paintingCanvasContainer.addView(paintingCanvasView)

    takeNoteLayoutBinding.colorPaletteInclude.strokeWidthSample.addView(strokePaintingCanvasView)

    if (themePreferences.checkLightDark()) {

        window.statusBarColor = getColor(R.color.light)
        window.navigationBarColor = getColor(R.color.light)

        takeNoteLayoutBinding.rootView.setBackgroundColor(getColor(R.color.light))
        takeNoteLayoutBinding.editTextTitleView.setBackgroundColor(getColor(R.color.lighter))

        takeNoteLayoutBinding.editTextContentView.setTextColor(getColor(R.color.dark))

        takeNoteLayoutBinding.toggleKeyboardHandwriting.backgroundTintList = ColorStateList.valueOf(getColor(R.color.default_color_light))
        takeNoteLayoutBinding.savingView.backgroundTintList = ColorStateList.valueOf(getColor(R.color.default_color_light))

    } else {

        window.statusBarColor = getColor(R.color.dark)
        window.navigationBarColor = getColor(R.color.dark)

        takeNoteLayoutBinding.rootView.setBackgroundColor(getColor(R.color.dark))
        takeNoteLayoutBinding.editTextTitleView.setBackgroundColor(getColor(R.color.darker))

        takeNoteLayoutBinding.editTextContentView.setTextColor(getColor(R.color.light))

        takeNoteLayoutBinding.toggleKeyboardHandwriting.backgroundTintList = ColorStateList.valueOf(getColor(R.color.default_color_dark))
        takeNoteLayoutBinding.savingView.backgroundTintList = ColorStateList.valueOf(getColor(R.color.default_color_dark))

    }

}