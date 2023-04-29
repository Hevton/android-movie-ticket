package woowacourse.movie.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityReservationResultBinding
import woowacourse.movie.domain.payment.PaymentType
import woowacourse.movie.uimodel.ReservationModel
import woowacourse.movie.uimodel.ReservationModel.Companion.RESERVATION_INTENT_KEY
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

class ReservationResultActivity : AppCompatActivity() {

    private val binding by lazy { ActivityReservationResultBinding.inflate(layoutInflater) }
    private val reservationModel: ReservationModel? by lazy {
        intent.getSerializableExtra(RESERVATION_INTENT_KEY) as? ReservationModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initReservationResultView()
    }

    private fun initReservationResultView() {
        if (reservationModel == null) return
        val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")

        reservationModel?.let {
            binding.movieNameTextView.text = it.movie.name.value
            binding.screeningDateTimeTextView.text = it.screeningDateTime.format(dateFormat)
            binding.ticketCountTextView.text = getString(R.string.ticket_count_and_seats_form)
                .format(it.ticketCount, it.seats.joinToString(", "))
            binding.paymentAmountTextView.text = getString(R.string.payment_amount_and_type_form)
                .format(
                    DecimalFormat("#,###").format(it.paymentAmount.value),
                    getPaymentTypeString(reservationModel!!.paymentType)
                )
        }
    }

    private fun getPaymentTypeString(paymentType: PaymentType): String =
        when (paymentType) {
            PaymentType.LOCAL_PAYMENT -> getString(R.string.payment_type_local_text)
        }
}
