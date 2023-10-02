package `in`.day1.a7minutesworkout

object Constants {
    fun defaultExerciseList() : ArrayList<ExerciseModel> {
        val exerciseList = ArrayList<ExerciseModel>()
        val jumpingJacks = ExerciseModel(
            1,
            "Jumping Jacks",
            R.drawable.ic_jumping_jacks,
            false,
            isSelected = false
        )
        exerciseList.add(jumpingJacks)

        val abdominalCrunch = ExerciseModel(
            2,
            "Abdominal Crunch",
            R.drawable.ic_abdominal_crunch,
            false,
            isSelected = false
        )
        exerciseList.add(abdominalCrunch)

        val highKnees = ExerciseModel(
            3,
            "High Knees running in place",
            R.drawable.ic_high_knees_running_in_place,
            false,
            isSelected = false
        )
        exerciseList.add(highKnees)

        val lunge = ExerciseModel(
            4,
            "Lunge",
            R.drawable.ic_lunge,
            false,
            isSelected = false
        )
        exerciseList.add(lunge)

        val plank = ExerciseModel(
            5,
            "Plank",
            R.drawable.ic_plank,
            false,
            isSelected = false
        )
        exerciseList.add(plank)

        val pushUp = ExerciseModel(
            6,
            "Push Up",
            R.drawable.ic_push_up,
            false,
            isSelected = false
        )
        exerciseList.add(pushUp)

        val pushUpRotate = ExerciseModel(
            7,
            "Push Up and Rotate",
            R.drawable.ic_push_up_and_rotation,
            false,
            isSelected = false
        )
        exerciseList.add(pushUpRotate)

        val sidePlank = ExerciseModel(
            8,
            "Side Plank",
            R.drawable.ic_side_plank,
            false,
            isSelected = false
        )
        exerciseList.add(sidePlank)

        val squat = ExerciseModel(
            9,
            "Squat",
            R.drawable.ic_squat,
            false,
            isSelected = false
        )
        exerciseList.add(squat)

        val stepUpChair = ExerciseModel(
            10,
            "Step Up onto Chair",
            R.drawable.ic_step_up_onto_chair,
            false,
            isSelected = false
        )
        exerciseList.add(stepUpChair)

        val tricepsDip = ExerciseModel(
            11,
            "Triceps Dip on Chair",
            R.drawable.ic_triceps_dip_on_chair,
            false,
            isSelected = false
        )
        exerciseList.add(tricepsDip)

        val wallSit = ExerciseModel(
            12,
            "Wall Sit",
            R.drawable.ic_wall_sit,
            false,
            isSelected = false
        )
        exerciseList.add(wallSit)

        return exerciseList
    }
}