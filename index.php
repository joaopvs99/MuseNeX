<?php
require 'vendor/autoload.php';
$stripe = new \Stripe\StripeClient('sk_test_51ObPBnJotQRASDIOhL5JiPS8TjS88auvQrL94uFW5VAouDozYcgSyzhADGiVgqEZa3QGQedUkjPKptNz97GPNwjV00w3b0ReGZ');

$paymentIntent = $stripe->paymentIntents->create([
  'amount' => 1099,
  'currency' => 'eur',
  'description' => 'Payment for Android',
  'automatic_payment_methods' => [
    'enabled' => 'true',
  ],
]);

echo json_encode(
  [
    'paymentIntent' => $paymentIntent->client_secret,
    'ephemeralKey' => $ephemeralKey->secret,
    'publishableKey' => 'pk_test_51ObPBnJotQRASDIOLDNy6HmbjsnaLOVu7K3qAROWCja2Cmi4gAtdxzb50OXTKLJ97JCp9ahZdjiVkf0cfUiDfEGl00kTz1Z6dF'
  ]
);
http_response_code(200);