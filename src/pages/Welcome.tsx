import React from 'react';
import { PageContainer } from '@ant-design/pro-layout';
// import { Card, Alert, Typography } from 'antd';
// import { useIntl, FormattedMessage } from 'umi';
import styles from './Welcome.less';

// const CodePreview: React.FC = ({ children }) => (
//   <pre className={styles.pre}>
//     <code>
//       <Typography.Text copyable>{children}</Typography.Text>
//     </code>
//   </pre>
// );

const Welcome: React.FC = () => {
  // const intl = useIntl();

  return (
    <PageContainer className={styles.container}>
      <div className={styles.slogan}>
        <i>FWF DESIGN</i>
      </div>
    </PageContainer>
  );
};

export default Welcome;
