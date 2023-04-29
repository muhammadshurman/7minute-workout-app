package mu.shurman.a7minuteworkout

/**
 *@Created_by : @Muhammad Al-Shurman on 4/14/2023
 *@E-mail: muhd.shurman@gmail.com
 */
object Constants {

    fun defaultExcerciseList():ArrayList<ExcerciseModel>{
        val excerciseList = ArrayList<ExcerciseModel>()

        val jumpingJacks = ExcerciseModel (
            1,
            "Jumping Jacks",
            R.drawable.jumping_jacks,
            isCompleted = false,
            isSelected = false
                )
        excerciseList.add(jumpingJacks)

        val WallSit = ExcerciseModel(2,
        "Wall Sit",
        R.drawable.wall_sit,
        isCompleted = false,
        isSelected = false)
        excerciseList.add(WallSit)

        val PushUp = ExcerciseModel(3,
        "Push Up",
        R.drawable.push_up,
        isCompleted = false,
        isSelected = false)
        excerciseList.add(PushUp)

        val abdominalCrunch = ExcerciseModel(4,
        "Abdominal Crunch",
        R.drawable.abdominal_crunches,
        isCompleted = false,
        isSelected = false)
        excerciseList.add(abdominalCrunch)

        val stepUpOnChair  = ExcerciseModel(5,
            "Set-Up on to chair",
            R.drawable.setuponchair,
            isCompleted = false,
            isSelected = false)
        excerciseList.add(stepUpOnChair)

        val squat   = ExcerciseModel(6,
            "Squat",
            R.drawable.squat,
            isCompleted = false,
            isSelected = false)
        excerciseList.add(squat)

        val tricepDipOnChair    = ExcerciseModel(7,
            "Tricep Dip On Chair",
            R.drawable.tricep_dip_on_chair,
            isCompleted = false,
            isSelected = false)
        excerciseList.add(tricepDipOnChair)

        val plank     = ExcerciseModel(8,
            "Plank",
            R.drawable.plank,
            isCompleted = false,
            isSelected = false)
        excerciseList.add(plank)

        val highKneesRunningInPlace      = ExcerciseModel(9,
            "High Knees Running In Place",
            R.drawable.high_knees_running_in_place,
            isCompleted = false,
            isSelected = false)
        excerciseList.add(highKneesRunningInPlace)

        val lunges       = ExcerciseModel(10,
            "Lunges",
            R.drawable.lunges,
            isCompleted = false,
            isSelected = false)
        excerciseList.add(lunges)

        val pushupAndRotation        = ExcerciseModel(11,
            "Push up and Rotation",
            R.drawable.pushup_and_rotation,
            isCompleted = false,
            isSelected = false)
        excerciseList.add(pushupAndRotation )

        val sidePlank         = ExcerciseModel(12,
            "Side Plank",
            R.drawable.side_plank,
            isCompleted = false,
            isSelected = false)
        excerciseList.add(sidePlank)
        return excerciseList
    }
}