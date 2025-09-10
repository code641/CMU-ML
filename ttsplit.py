import pandas as pd
from sklearn.model_selection import train_test_split

pd.set_option('display.max_columns', 6)
pd.set_option('display.max_rows', 5)

kddcup_path = 'kddcup.data.gz'
corrected_path = 'corrected.gz'

kddcup_df = pd.read_csv(kddcup_path, compression='gzip', header=None)
corrected_df = pd.read_csv(corrected_path, compression='gzip', header=None)

df = pd.concat([kddcup_df, corrected_df], ignore_index=True)

print("Read {} rows.".format(len(df)))
df.dropna(inplace=True, axis=1)

#label names are courtesy of a KDDCup Data file analysis video
df.columns = [
    'duration', 'protocol_type', 'service', 'flag', 'src_bytes', 'dst_bytes',
    'land', 'wrong_fragment', 'urgent', 'hot', 'num_failed_logins', 'logged_in',
    'num_compromised', 'root_shell', 'su_attempted', 'num_root', 'num_file_creations',
    'num_shells', 'num_access_files', 'num_outbound_cmds', 'is_host_login', 'is_guest_login',
    'count', 'srv_count', 'serror_rate', 'srv_serror_rate', 'rerror_rate', 'srv_rerror_rate',
    'same_srv_rate', 'diff_srv_rate', 'srv_diff_host_rate', 'dst_host_count', 'dst_host_srv_count',
    'dst_host_same_srv_rate', 'dst_host_diff_srv_rate', 'dst_host_same_src_port_rate',
    'dst_host_srv_diff_host_rate', 'dst_host_serror_rate', 'dst_host_srv_serror_rate',
    'dst_host_rerror_rate', 'dst_host_srv_rerror_rate', 'outcome'
]

unique_labels = df['outcome'].unique()

final_train_df = pd.DataFrame(columns=df.columns)
final_test_df = pd.DataFrame(columns=df.columns)

for label in unique_labels:
    label_df = df[df['outcome'] == label]
    train_label_df, test_label_df = train_test_split(label_df, test_size=0.2, random_state=42)
    
    final_train_df = pd.concat([final_train_df, train_label_df])
    final_test_df = pd.concat([final_test_df, test_label_df])

train_labels = final_train_df['outcome']
test_labels = final_test_df['outcome']

print('Training set distribution:')
print(train_labels.value_counts(normalize=True))
print('\nTest set distribution:')
print(test_labels.value_counts(normalize=True))

final_train_df.to_csv('new_train_data.csv', index=False)
final_test_df.to_csv('new_test_data.csv', index=False)

print("New training set:")
print(final_train_df.head())
print("\nNew test set:")
print(final_test_df.head())
